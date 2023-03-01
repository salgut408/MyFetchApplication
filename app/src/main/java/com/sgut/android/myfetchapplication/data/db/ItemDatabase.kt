package com.sgut.android.myfetchapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sgut.android.myfetchapplication.domain.domain_models.ItemDomainModel

@Database(
    entities = [ItemDomainModel::class, FavoriteItem::class],
    version = 3,
    exportSchema = false
)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun getDao(): ItemDao
}