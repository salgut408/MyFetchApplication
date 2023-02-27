package com.sgut.android.myfetchapplication.domain

import com.sgut.android.myfetchapplication.data.db.FavoriteItem
import com.sgut.android.myfetchapplication.domain.domain_models.ItemDomainModel
import com.sgut.android.myfetchapplication.ui.screens.ItemUiModel

interface ItemsRepositoryInterface {

    suspend fun getSortedListExNullsExBlanksFromRepository(): List<ItemDomainModel>
    suspend fun getAllInfoFromRepository(): List<ItemDomainModel>
    suspend fun getFavorites(): List<FavoriteItem>
    suspend fun saveFavoriteItem(item: ItemDomainModel)
    suspend fun deleteFavorteItem(item: ItemDomainModel)
    suspend fun getInfoForDatabaseNoSort(): Unit

}