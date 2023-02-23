package com.sgut.android.myfetchapplication.data.domain_models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class ItemDomainModel(
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null,
    var listId: Int? = null,
    var name: String? = null,

)

fun ItemDomainModel.asItemFavoritesDomainModel(): FavoriteItem {
    return FavoriteItem(
        id = id,
        listId = listId,
        name = name,
        favId = 0
    )
}
