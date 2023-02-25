package com.sgut.android.myfetchapplication.data.db

import androidx.room.*
import com.sgut.android.myfetchapplication.data.domain_models.FavoriteItem
import com.sgut.android.myfetchapplication.data.domain_models.ItemDomainModel

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item: ItemDomainModel): Long

    //this returns unsorted list from db
    @Query("SELECT * FROM item_table")
    suspend fun getAllInfoFromDb(): List<ItemDomainModel>

    //this returns a sorted list from db
    @Query("SELECT * FROM item_table WHERE NULLIF(name, '') IS NOT NULL ORDER BY listId ASC ")
    suspend fun getInfoFromDbSortByListIdExNullsExBlanks(): List<ItemDomainModel>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteItem(item: FavoriteItem)

    @Query("SELECT * FROM favorite_item_table")
    suspend fun getAllFavoriteItems(): List<FavoriteItem>

    @Query("DELETE FROM favorite_item_table")
    suspend fun deleteFavoriteItemTable()

    @Delete
    suspend fun delete(item: FavoriteItem)



}
