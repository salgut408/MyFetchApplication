package com.sgut.android.myfetchapplication.data.remote.api

import com.sgut.android.myfetchapplication.data.remote.network_responses.NetworkItem
import retrofit2.Response
import retrofit2.http.GET

interface FetchApi {
    @GET("hiring.json")
    suspend fun getFetchInformation() : Response<List<NetworkItem>>
}