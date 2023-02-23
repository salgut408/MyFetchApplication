package com.sgut.android.myfetchapplication.data.repository

import android.util.Log
import com.sgut.android.myfetchapplication.data.db.ItemDao
import com.sgut.android.myfetchapplication.data.db.ItemDatabase
import com.sgut.android.myfetchapplication.data.domain_models.FavoriteItem
import com.sgut.android.myfetchapplication.data.domain_models.ItemDomainModel
import com.sgut.android.myfetchapplication.data.domain_models.asItemFavoritesDomainModel
import com.sgut.android.myfetchapplication.data.dto_mappers.NetworkItemDtoMapperImpl
import com.sgut.android.myfetchapplication.data.remote.api.FetchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemRepository @Inject constructor(
    val networkItemDtoMapperImpl: NetworkItemDtoMapperImpl,
    val dao: ItemDao,
    val api: FetchApi,
    val itemDatabase: ItemDatabase
) {

    suspend fun getSortedListExNullsExBlanks(): List<ItemDomainModel> {
        val result = dao.getInfoSortByListIdExNullsExBlanks()
        return result
    }

    suspend fun getFavorites(): List<FavoriteItem> {
        val result = dao.getAllFavoriteItems()
        return result
    }

    //repository is central place for all data changes

    suspend fun saveFavoriteItem(item: ItemDomainModel){
        itemDatabase.getDao().insertFavoriteItem(item.asItemFavoritesDomainModel())
    }

    suspend fun deleteFavorteItem(item: ItemDomainModel) {
        itemDatabase.getDao().delete(item.asItemFavoritesDomainModel())
    }

    suspend fun getInfoForDatabase() {
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