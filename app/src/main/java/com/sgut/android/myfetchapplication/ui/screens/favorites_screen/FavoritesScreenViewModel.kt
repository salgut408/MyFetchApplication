package com.sgut.android.myfetchapplication.ui.screens.favorites_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.myfetchapplication.data.repository.ItemRepositoryImpl
import com.sgut.android.myfetchapplication.domain.ItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoritesScreenViewModel @Inject constructor(
    private val itemsRepository: ItemRepositoryImpl,
): ViewModel() {

    private val _favoritesScreenUiState = MutableStateFlow(FavoritesScreenUiState())
    val favoritesScreenUiState: SharedFlow<FavoritesScreenUiState> = _favoritesScreenUiState.asSharedFlow()

    init {
        showFavoriteItems()
    }

    private fun showFavoriteItems() = viewModelScope.launch {
        try {
            itemsRepository.getFavorites().collect { itemsList ->
                _favoritesScreenUiState.update { currentState ->
                    currentState.copy(
                        favoriteItems = itemsList
                    )
                }
            }
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
        }
    }

}