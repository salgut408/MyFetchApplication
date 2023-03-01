package com.sgut.android.myfetchapplication.data.repository

import android.util.Log
import com.sgut.android.myfetchapplication.data.db.FavoriteItem
import com.sgut.android.myfetchapplication.data.db.ItemDao
import com.sgut.android.myfetchapplication.data.db.ItemDatabase
import com.sgut.android.myfetchapplication.data.remote.api.FetchApi
import com.sgut.android.myfetchapplication.domain.ItemsRepository
import com.sgut.android.myfetchapplication.domain.domain_models.ItemDomainModel
import com.sgut.android.myfetchapplication.domain.domain_models.asItemFavoritesDomainModel
import com.sgut.android.myfetchapplication.domain.domain_models.dto_mappers.NetworkItemDtoMapperImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

//ItemRepositoryImpl Implementation

class ItemRepositoryImpl @Inject constructor(
    val networkItemDtoMapperImpl: NetworkItemDtoMapperImpl,
    val dao: ItemDao,
    val api: FetchApi,
    val itemDatabase: ItemDatabase
): ItemsRepository {

    //this gets sorted list from sql in db


    // this gets all info, not sorted
    override  suspend fun getAllInfoFromRepository(): Flow<List<ItemDomainModel>> {
        val result = dao.getAllInfoFromDb()
        return result

    }

    //this gets all favorite items from favorites table in db
    override suspend fun getFavorites(): Flow<List<FavoriteItem>> {
        val result = dao.getAllFavoriteItems()
        return result
    }



    //repository is central place for all data changes so this is where extension f(x)'s called

    override  suspend fun saveFavoriteItem(item: ItemDomainModel){
        itemDatabase.getDao().insertFavoriteItem(item.asItemFavoritesDomainModel())
    }

    override suspend fun deleteFavorteItem(item: ItemDomainModel) {
        itemDatabase.getDao().delete(item.asItemFavoritesDomainModel())
    }



    //calls for all items to populate databaase no sorting
    override suspend fun getInfoForDatabaseNoSort() {
        withContext(Dispatchers.IO) {
            try {
                val items = api.getFetchInformation().body()
                    ?.let { networkItemDtoMapperImpl.toDomainList(it) }
                items?.map{dao.update(it)}
            } catch (err: Exception) {
                Log.i("Tag", "Failed, ${err.message.toString()}")
            }
        }
    }
}