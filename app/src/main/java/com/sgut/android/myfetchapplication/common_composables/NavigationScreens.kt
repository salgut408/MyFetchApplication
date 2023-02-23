package com.sgut.android.myfetchapplication.common_composables
import com.sgut.android.myfetchapplication.R.string as AppText


sealed class NavigationScreens(val route: String){
    object MainScreen: NavigationScreens(route = "Items")
    object Favorites: NavigationScreens(route = "Favorites")
}
