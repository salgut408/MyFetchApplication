package com.sgut.android.myfetchapplication.ui.screens.main_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.sgut.android.myfetchapplication.domain.domain_models.ItemDomainModel
import com.sgut.android.myfetchapplication.data.repository.ItemRepository
import com.sgut.android.myfetchapplication.utils.ItemComparator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
): ViewModel(){

    private val _MainScreenUiState = MutableStateFlow<MainScreenUiState>(MainScreenUiState())
    val mainScreenUiState: StateFlow<MainScreenUiState> = _MainScreenUiState.asStateFlow()


    init {
        callInfoForDB()
//        showInfoFromRepositorySorted()
        showAllItemsSortAndFilteredInVm()
//        showAllItemsFlow()

    }

    fun onRemovefromFavoritesClick(item: ItemDomainModel) = viewModelScope.launch {
        itemRepository.deleteFavorteItem(item)
    }

    fun onAddToFavoritesClick(item: ItemDomainModel) = viewModelScope.launch {
        itemRepository.saveFavoriteItem(item)
    }


    // this is supposed to sort with comparator in the Viewmodel

    private fun showAllItemsSortAndFilteredInVm() = viewModelScope.launch {
        try {
            val result = itemRepository.getAllInfoFromRepository()
            _MainScreenUiState.update { currentState ->
                currentState.copy(
                    currentItems = result
                        .sortedWith(ItemComparator)
                        .filter { it.name?.isNotEmpty() ?: false },
                    isLoading = false
                )
            }
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
        }
    }


// this makes initial api call
    private fun callInfoForDB() = viewModelScope.launch {
        itemRepository.getInfoForDatabaseNoSort()
    }

// this calls pre sorted info from repository/database
//   private fun showInfoFromRepositorySorted() = viewModelScope.launch {
//       try {
//
//           val result = itemRepository.getSortedListExNullsExBlanksFromRepository()
//           _MainScreenUiState.update { currentState ->
//               currentState.copy(
//                   currentItems = result,
//               )
//           }
//       } catch (e: Exception) {
//           Log.e("Error", e.message.toString())
//       }
//    }
}