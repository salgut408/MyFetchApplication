package com.sgut.android.myfetchapplication.data.remote.network_responses

import com.google.gson.annotations.SerializedName

data class NetworkItem(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("listId") val listId: Int? = null,
    @SerializedName("name") val name: String? = null,
)
