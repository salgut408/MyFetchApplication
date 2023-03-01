package com.sgut.android.myfetchapplication.ui.screens.main_screen

import com.sgut.android.myfetchapplication.domain.domain_models.ItemDomainModel



sealed interface MainScreenUiState {
    object Loading: MainScreenUiState
    data class Content(val itemsList: List<ItemDomainModel>): MainScreenUiState
}