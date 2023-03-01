package com.sgut.android.myfetchapplication.data.repository

import android.util.Log
import com.sgut.android.myfetchapplication.data.db.FavoriteItem
import com.sgut.android.myfetchapplication.data.db.ItemDao
import com.sgut.android.myfetchapplication.data.db.ItemDatabase
import com.sgut.android.myfetchapplication.data.remote.api.FetchApi
import com.sgut.android.myfetchapplication.domain.ItemsRepository
import com.sgut.android.myfetchapplication.domain.domain_models.ItemDomainModel
import com.sgut.android.myfetchapplication.domain.domain_models.asFavoriteItem
import com.sgut.android.myfetchapplication.domain.domain_models.dto_mappers.NetworkItemDtoMapperImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    val networkItemDtoMapperImpl: NetworkItemDtoMapperImpl,
    val dao: ItemDao,
    val api: FetchApi,
    val itemDatabase: ItemDatabase
): ItemsRepository {
    // this gets all info, not sorted
    override  suspend fun getAllInfoFromRepository(): Flow<List<ItemDomainModel>> {
        return  dao.getAllInfoFromDb()
    }

    //this gets all favorite items from favorites table in db
    override suspend fun getFavorites(): Flow<List<FavoriteItem>> {
       return dao.getAllFavoriteItems()
    }

    override  suspend fun saveFavoriteItem(item: ItemDomainModel){
        itemDatabase.getDao().insertFavoriteItem(item.asFavoriteItem())
    }

    override suspend fun deleteFavorteItem(item: ItemDomainModel) {
        itemDatabase.getDao().delete(item.asFavoriteItem())
    }

    //calls for all items to populate database no sorting
    override suspend fun getInfoForDatabaseNoSort() {
        withContext(Dispatchers.IO) {
            try {
                val items = api.getFetchInformation().body()
                    ?.let { networkItemDtoMapperImpl.toDomainList(it) }
                items?.map{ dao.update(it) }
            } catch (err: Exception) {
                Log.e("Repository", "Failed, ${err.message.toString()}")
            }
        }
    }
}