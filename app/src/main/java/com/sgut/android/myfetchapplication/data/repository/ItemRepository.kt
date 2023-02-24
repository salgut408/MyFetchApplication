package com.sgut.android.myfetchapplication.data.repository

import android.util.Log
import com.sgut.android.myfetchapplication.data.db.ItemDao
import com.sgut.android.myfetchapplication.data.db.ItemDatabase
import com.sgut.android.myfetchapplication.data.domain_models.FavoriteItem
import com.sgut.android.myfetchapplication.data.domain_models.ItemDomainModel
import com.sgut.android.myfetchapplication.data.domain_models.asItemFavoritesDomainModel
import com.sgut.android.myfetchapplication.data.dto_mappers.NetworkItemDtoMapperImpl
import com.sgut.android.myfetchapplication.data.remote.api.FetchApi
import com.sgut.android.myfetchapplication.utils.ItemComparator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

//ItemRepository Implementation

class ItemRepository @Inject constructor(
    val networkItemDtoMapperImpl: NetworkItemDtoMapperImpl,
    val dao: ItemDao,
    val api: FetchApi,
    val itemDatabase: ItemDatabase
): ItemsRepositoryInterface {

    //this gets sorted list from sql in db
    override suspend fun getSortedListExNullsExBlanksFromRepository(): List<ItemDomainModel> {
        val result = dao.getInfoFromDbSortByListIdExNullsExBlanks()
        return result
    }

// this gets all info, not sorted
    override  suspend fun getAllInfoFromRepository(): List<ItemDomainModel> {
        val result = dao.getAllInfoFromDb()
        return result

    }

//this gets all favorites
    override suspend fun getFavorites(): List<FavoriteItem> {
        val result = dao.getAllFavoriteItems()
        return result
    }



    //repository is central place for all data changes

    override  suspend fun saveFavoriteItem(item: ItemDomainModel){
        itemDatabase.getDao().insertFavoriteItem(item.asItemFavoritesDomainModel())
    }

    override suspend fun deleteFavorteItem(item: ItemDomainModel) {
        itemDatabase.getDao().delete(item.asItemFavoritesDomainModel())
    }



    override suspend fun clearTable() {
        itemDatabase.getDao().deleteFavoriteItemTable()
    }

    //calls for all infor for databaase no sort
    override suspend fun getInfoForDatabaseNoSort() {
        withContext(Dispatchers.IO) {
            try {
                val items = api.getFetchInformation().body()
                    ?.let { networkItemDtoMapperImpl.toDomainList(it) }
                items?.map{dao.update(it)}
            } catch (err: Exception) {
                Log.i("Tag", "Failed")
            }
        }
    }
}