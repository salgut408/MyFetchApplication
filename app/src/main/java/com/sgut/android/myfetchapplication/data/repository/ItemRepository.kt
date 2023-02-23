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


    suspend fun saveFavoriteItem(item: ItemDomainModel){
        itemDatabase.getDao().insertFavoriteItem(item.asItemFavoritesDomainModel())
    }

    suspend fun getInfoForDatabase() {
        withContext(Dispatchers.IO) {
            try {
                val items = api.getFetchInformation().body()!!
                val items2 = networkItemDtoMapperImpl.toDomainList(items)

                items2.map{dao.update(it)}


            } catch (err: Exception) {
                Log.i("Tag", "Failed")
            }
        }
    }
}