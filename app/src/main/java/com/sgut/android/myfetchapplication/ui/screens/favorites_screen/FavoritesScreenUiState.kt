package com.sgut.android.myfetchapplication.ui.screens.favorites_screen

import com.sgut.android.myfetchapplication.data.db.FavoriteItem

sealed interface FavoritesScreenUiState{
    object IsEmpty: FavoritesScreenUiState
    data class Content(val favItemsList: List<FavoriteItem>) :FavoritesScreenUiState
}