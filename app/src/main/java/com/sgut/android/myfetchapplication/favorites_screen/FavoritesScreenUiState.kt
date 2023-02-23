package com.sgut.android.myfetchapplication.favorites_screen

import com.sgut.android.myfetchapplication.data.domain_models.FavoriteItem

data class FavoritesScreenUiState(
    var favoriteItems: List<FavoriteItem> = listOf(),
)