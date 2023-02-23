package com.sgut.android.myfetchapplication.data.db

import androidx.room.*
import com.sgut.android.myfetchapplication.data.domain_models.FavoriteItem
import com.sgut.android.myfetchapplication.data.domain_models.ItemDomainModel
import retrofit2.http.DELETE

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteItem(item: FavoriteItem)

    @Query("SELECT * FROM favorite_item_table")
    suspend fun getAllFavoriteItems(): List<FavoriteItem>

    @Delete
    suspend fun delete(item: FavoriteItem)

}
