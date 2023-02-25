package com.sgut.android.myfetchapplication.data.domain_models

import androidx.room.Entity
import androidx.room.PrimaryKey

// seperate domain and database objs ?

@Entity(tableName = "favorite_item_table")
data class FavoriteItem(

    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val listId: Int? = null,
    val name: String? = null,

)
