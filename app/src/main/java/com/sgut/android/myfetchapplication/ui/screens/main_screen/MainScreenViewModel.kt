package com.sgut.android.myfetchapplication.ui.screens.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.myfetchapplication.data.repository.ItemRepositoryImpl
import com.sgut.android.myfetchapplication.domain.domain_models.ItemDomainModel
import com.sgut.android.myfetchapplication.utils.ItemComparator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val itemsRepository: ItemRepositoryImpl,
): ViewModel(){

    // i dont think i needed to make this sharedFlow
    private val _mainScreenUiState = MutableStateFlow<MainScreenUiState>(MainScreenUiState.Loading)
    val mainScreenUiState: StateFlow<MainScreenUiState> = _mainScreenUiState

    init {
        callInfoForDB()
        showAllItemsSortAndFilteredInVm()
    }

    fun onRemovefromFavoritesClick(item: ItemDomainModel) = viewModelScope.launch {
        itemsRepository.deleteFavorteItem(item)
    }

    fun onAddToFavoritesClick(item: ItemDomainModel) = viewModelScope.launch {
        itemsRepository.saveFavoriteItem(item)
    }

    //  sorts with comparator in the ViewModel and emits it
    private fun showAllItemsSortAndFilteredInVm() = viewModelScope.launch {
        try {
            itemsRepository.getAllInfoFromRepository().collect { allItems ->
                _mainScreenUiState.emit(MainScreenUiState.Content(allItems.sortedWith(ItemComparator).filter { it.name?.isNotBlank() ?: false }))
            }
        } catch (e: Exception){
            Log.e("Error-Main viewModel", e.message.toString())
        }
    }

// this makes initial api call
    private fun callInfoForDB() = viewModelScope.launch {
        itemsRepository.getInfoForDatabaseNoSort()
    }
}


