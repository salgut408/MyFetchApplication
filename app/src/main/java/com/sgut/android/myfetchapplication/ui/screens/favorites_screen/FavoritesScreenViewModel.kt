package com.sgut.android.myfetchapplication.ui.screens.favorites_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.myfetchapplication.data.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class FavoritesScreenViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
): ViewModel() {

    private val _FavoritesScreenUiState = MutableStateFlow(FavoritesScreenUiState())
    val favoritesScreenUiState: SharedFlow<FavoritesScreenUiState> = _FavoritesScreenUiState.asSharedFlow()

    init {
        showFavoriteItems()
    }

    private fun showFavoriteItems() = viewModelScope.launch {
        try {
            itemRepository.getFavorites().collect { itemsList ->
                _FavoritesScreenUiState.update { currentState ->
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