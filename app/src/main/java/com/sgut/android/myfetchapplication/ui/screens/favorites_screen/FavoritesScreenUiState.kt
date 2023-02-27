package com.sgut.android.myfetchapplication.ui.screens.favorites_screen

import com.sgut.android.myfetchapplication.data.db.FavoriteItem

data class FavoritesScreenUiState(
    var favoriteItems: List<FavoriteItem> = listOf(),
)