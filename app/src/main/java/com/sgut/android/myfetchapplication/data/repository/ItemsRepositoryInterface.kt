package com.sgut.android.myfetchapplication.data.repository

import com.sgut.android.myfetchapplication.data.domain_models.FavoriteItem
import com.sgut.android.myfetchapplication.data.domain_models.ItemDomainModel

interface ItemsRepositoryInterface {

    suspend fun getSortedListExNullsExBlanksFromRepository(): List<ItemDomainModel>
    suspend fun getAllInfoFromRepository(): List<ItemDomainModel>
    suspend fun getFavorites(): List<FavoriteItem>
    suspend fun saveFavoriteItem(item: ItemDomainModel)
    suspend fun deleteFavorteItem(item: ItemDomainModel)
    suspend fun getInfoForDatabaseNoSort(): Unit
    suspend fun clearTable(): Unit

}