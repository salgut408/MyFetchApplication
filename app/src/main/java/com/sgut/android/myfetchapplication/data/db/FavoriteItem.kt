package com.sgut.android.myfetchapplication.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

// database objs

@Entity(tableName = "favorite_item_table")
data class FavoriteItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val listId: Int? = null,
    val name: String? = null,

)
