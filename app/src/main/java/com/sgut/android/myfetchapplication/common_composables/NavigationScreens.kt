package com.sgut.android.myfetchapplication.common_composables

sealed class NavigationScreens(val route: String){
    object MainScreen: NavigationScreens(route = "items_list")
    object Favorites: NavigationScreens(route = "favorites")

}
