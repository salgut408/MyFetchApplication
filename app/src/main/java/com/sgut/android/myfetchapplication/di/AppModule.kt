package com.sgut.android.myfetchapplication.di

import android.content.Context
import androidx.room.Room
import com.sgut.android.myfetchapplication.utils.Constants.Companion.BASE_URL
import com.sgut.android.myfetchapplication.data.repository.ItemRepository
import com.sgut.android.myfetchapplication.data.db.ItemDao
import com.sgut.android.myfetchapplication.data.db.ItemDatabase
import com.sgut.android.myfetchapplication.data.dto_mappers.NetworkItemDtoMapperImpl
import com.sgut.android.myfetchapplication.data.remote.api.FetchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideItemDao(itemDatabase: ItemDatabase): ItemDao = itemDatabase.getDao()

    @Provides
    fun provideMapper(): NetworkItemDtoMapperImpl = NetworkItemDtoMapperImpl()

    @Provides
    @Singleton
    fun provideItemDatabase(@ApplicationContext context: Context): ItemDatabase =
        Room.databaseBuilder(
            context,
            ItemDatabase::class.java,
            "database"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideItemRepository(
        networkItemDtoMapperImpl: NetworkItemDtoMapperImpl,
        dao: ItemDao,
        api: FetchApi,
        itemDatabase: ItemDatabase
    ): ItemRepository = ItemRepository(
        networkItemDtoMapperImpl,
        dao,
        api,
        itemDatabase
    )

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()

    @Singleton
    @Provides
    fun provideApi(okhttpClient: OkHttpClient): FetchApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FetchApi::class.java)
}