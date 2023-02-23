package com.sgut.android.myfetchapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sgut.android.myfetchapplication.data.domain_models.FavoriteItem
import com.sgut.android.myfetchapplication.data.domain_models.ItemDomainModel

@Database(
    entities = [ItemDomainModel::class, FavoriteItem::class],
    version = 2,
    exportSchema = false
)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun getDao(): ItemDao

}