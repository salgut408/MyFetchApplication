package com.sgut.android.myfetchapplication.ui.screens.favorites_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.myfetchapplication.data.db.FavoriteItem
import com.sgut.android.myfetchapplication.data.repository.ItemRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoritesScreenViewModel @Inject constructor(
    private val itemsRepository: ItemRepositoryImpl,
): ViewModel() {

    private val _favoritesScreenUiState = MutableStateFlow<FavoritesScreenUiState>(FavoritesScreenUiState.IsEmpty)
    val favoritesScreenUiState: SharedFlow<FavoritesScreenUiState> = _favoritesScreenUiState.asSharedFlow()

    init {
        showFavoriteItems()
    }

    private fun showFavoriteItems() = viewModelScope.launch {
        try {
            itemsRepository.getFavorites().collect { itemsList ->
                _favoritesScreenUiState.emit(FavoritesScreenUiState.Content(itemsList))
            }
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
        }
    }
}

