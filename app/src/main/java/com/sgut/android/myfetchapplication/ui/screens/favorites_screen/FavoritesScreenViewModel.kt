package com.sgut.android.myfetchapplication.ui.screens.favorites_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.myfetchapplication.data.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoritesScreenViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
): ViewModel() {

    private val _FavoritesScreenUiState = MutableStateFlow(FavoritesScreenUiState())
    val favoritesScreenUiState: StateFlow<FavoritesScreenUiState> = _FavoritesScreenUiState.asStateFlow()


    init {
        showFavoriteItems()
    }




    private fun showFavoriteItems() = viewModelScope.launch {
        val result = itemRepository.getFavorites()

        _FavoritesScreenUiState.update { currentState ->
            currentState.copy(
                favoriteItems = result
            )
        }
    }

}