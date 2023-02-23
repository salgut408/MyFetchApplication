package com.sgut.android.myfetchapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sgut.android.myfetchapplication.data.domain_models.ItemDomainModel

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item: ItemDomainModel): Long

    @Query("SELECT * FROM item_table ORDER BY listId ASC")
    suspend fun getInfoSortByListId(): List<ItemDomainModel>

    @Query("SELECT * FROM item_table WHERE name IS NOT NULL ORDER BY listId ASC ")
    suspend fun getInfoSortByListIdExNulls(): List<ItemDomainModel>

    @Query("SELECT * FROM item_table WHERE NULLIF(name, '') IS NOT NULL ORDER BY listId ASC ")
    suspend fun getInfoSortByListIdExNullsExBlanks(): List<ItemDomainModel>

}
