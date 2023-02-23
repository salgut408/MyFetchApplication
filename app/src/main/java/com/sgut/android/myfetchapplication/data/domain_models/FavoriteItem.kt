package com.sgut.android.myfetchapplication.data.domain_models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_item_table")
data class FavoriteItem(
    var id: Int? = null,
    var listId: Int? = null,
    var name: String? = null,
    @PrimaryKey(autoGenerate = true)
    var favId: Int
)
