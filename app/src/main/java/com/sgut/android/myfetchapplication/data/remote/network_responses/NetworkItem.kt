package com.sgut.android.myfetchapplication.data.remote.network_responses

import com.google.gson.annotations.SerializedName

data class NetworkItem(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("listId") var listId: Int? = null,
    @SerializedName("name") var name: String? = null,
)
