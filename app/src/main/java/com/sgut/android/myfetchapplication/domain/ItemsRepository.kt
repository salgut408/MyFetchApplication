package com.sgut.android.myfetchapplication.domain

import com.sgut.android.myfetchapplication.data.db.FavoriteItem
import com.sgut.android.myfetchapplication.domain.domain_models.ItemDomainModel
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    suspend fun getAllInfoFromRepository(): Flow<List<ItemDomainModel>>
    suspend fun getFavorites(): Flow<List<FavoriteItem>>
    suspend fun saveFavoriteItem(item: ItemDomainModel)
    suspend fun deleteFavorteItem(item: ItemDomainModel)
    suspend fun getInfoForDatabaseNoSort(): Unit

}