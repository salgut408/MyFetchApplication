package com.sgut.android.myfetchapplication.domain.domain_models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sgut.android.myfetchapplication.data.db.FavoriteItem
import com.sgut.android.myfetchapplication.ui.screens.ItemUiModel


@Entity(tableName = "item_table")
data class ItemDomainModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val listId: Int? = null,
    val name: String? = null,
)

fun ItemDomainModel.asItemFavoritesDomainModel(): FavoriteItem {
    return FavoriteItem(
        id = id,
        listId = listId,
        name = name,
    )
}

fun ItemDomainModel.asItemUiModel(): ItemUiModel {
    return ItemUiModel(
        id = id,
        listId = listId,
        name = name
    )
}

