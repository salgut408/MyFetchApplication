package com.sgut.android.myfetchapplication.common_composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sgut.android.myfetchapplication.MyFetchAppState
import com.sgut.android.myfetchapplication.ui.screens.favorites_screen.FavoitesScreen
import com.sgut.android.myfetchapplication.ui.screens.main_screen.MainScreen

//navigation content set up

@Composable
fun Navigation(
    appState: MyFetchAppState,
    padding: PaddingValues,
) {
    NavHost(
        navController = appState.navController,
        startDestination = NavigationScreens.MainScreen.route,
        modifier = Modifier.padding(padding)

    ) {
        composable(
            route = NavigationScreens.MainScreen.route
        ) {
            MainScreen()
        }
        composable(
            route = NavigationScreens.Favorites.route
        ) {
            FavoitesScreen()
        }
    }

}