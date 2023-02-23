package com.sgut.android.myfetchapplication.main_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.myfetchapplication.data.domain_models.FavoriteItem
import com.sgut.android.myfetchapplication.data.domain_models.ItemDomainModel
import com.sgut.android.myfetchapplication.data.repository.ItemRepository
import com.sgut.android.myfetchapplication.utils.ItemComparator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
): ViewModel(){

    private val _MainScreenUiState = MutableStateFlow(MainScreenUiState())
    val mainScreenUiState: StateFlow<MainScreenUiState> = _MainScreenUiState.asStateFlow()

    private var itemsList = mutableStateOf<List<ItemDomainModel>>(listOf())

    init {
        getInforForDb()

    }


    fun onAddToFavoritesClick(item: ItemDomainModel) = viewModelScope.launch {
        itemRepository.saveFavoriteItem(item)
    }

    private fun loadItems() = viewModelScope.launch {
        try {
            val result = itemRepository.getSortedListExNullsExBlanks()
            _MainScreenUiState.update { currentState ->
                currentState.copy(
                    currentItems = result
                )
            }
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
        }
    }

   private fun getInforForDb() = viewModelScope.launch {
        itemRepository.getInfoForDatabase()
        val result = itemRepository.getSortedListExNullsExBlanks()
        _MainScreenUiState.update { currentState ->
            currentState.copy(
                currentItems = result.sortedWith(ItemComparator)
            )
        }
    }
}