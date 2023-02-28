package com.sgut.android.myfetchapplication.ui.screens.main_screen

import com.sgut.android.myfetchapplication.domain.domain_models.ItemDomainModel

data class MainScreenUiState(
    val currentItems: List<ItemDomainModel> = listOf(),
    val isLoading: Boolean = true
)