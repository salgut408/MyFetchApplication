package com.sgut.android.myfetchapplication.common_composables
import com.sgut.android.myfetchapplication.R.string as AppText

// navigation screens
sealed class NavigationScreens(val route: String){
    object MainScreen: NavigationScreens(route = "Items")
    object Favorites: NavigationScreens(route = "Favorites")
}
