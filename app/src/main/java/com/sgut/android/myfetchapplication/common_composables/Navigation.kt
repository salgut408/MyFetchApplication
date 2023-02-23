package com.sgut.android.myfetchapplication.common_composables

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sgut.android.myfetchapplication.MyFetchAppState
import com.sgut.android.myfetchapplication.main_screen.MainScreen

@Composable
fun Navigation(
    appState: MyFetchAppState,
) {
    NavHost(
        navController = appState.navController,
        startDestination = NavigationScreens.MainScreen.route
    ) {
        composable(
            route = NavigationScreens.MainScreen.route
        ) {
            MainScreen(
                navController = appState.navController
            )
        }
    }

}