package com.sgut.android.myfetchapplication.main_screen

import com.sgut.android.myfetchapplication.domain_models.ItemDomainModel

data class MainScreenUiState(
    var currentItems: List<ItemDomainModel> = listOf(),
)