package com.sgut.android.myfetchapplication.data.db

import androidx.room.*
import com.sgut.android.myfetchapplication.domain.domain_models.ItemDomainModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item: ItemDomainModel): Long

    //this returns unsorted list from db
    @Query("SELECT * FROM item_table")
     fun getAllInfoFromDb(): Flow<List<ItemDomainModel>>

    //this returns a sorted list from db
    @Query("SELECT * FROM item_table WHERE NULLIF(name, '') IS NOT NULL ORDER BY listId ASC ")
    suspend fun getInfoFromDbSortByListIdExNullsExBlanks(): List<ItemDomainModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteItem(item: FavoriteItem)

    @Query("SELECT * FROM favorite_item_table")
     fun getAllFavoriteItems(): Flow<List<FavoriteItem>>

    @Delete
    suspend fun delete(item: FavoriteItem)

}
