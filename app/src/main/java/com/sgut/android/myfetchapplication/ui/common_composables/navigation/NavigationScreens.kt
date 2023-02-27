package com.sgut.android.myfetchapplication.ui.common_composables.navigation

// navigation screens
sealed class NavigationScreens(val route: String){
    object MainScreen: NavigationScreens(route = "Items")
    object Favorites: NavigationScreens(route = "Favorites")
}
