package com.sgut.android.myfetchapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sgut.android.myfetchapplication.ui.common_composables.MainToolBar
import com.sgut.android.myfetchapplication.ui.common_composables.Navigation
import com.sgut.android.myfetchapplication.ui.common_composables.navigation.NavigationScreens
import com.sgut.android.myfetchapplication.ui.screens.MyFetchAppState

    //App level container
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyFetchApp(
    modifier: Modifier = Modifier,
) {
    val appState = rememberAppState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val backStackEntry by appState.navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MainToolBar(
                currentScreen = backStackEntry?.destination?.route ?: NavigationScreens.MainScreen.route,
                canNavigateBack = appState.navController.previousBackStackEntry != null,
                navigateUp = { appState.navController.navigateUp() },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            BottomAppBar(modifier = modifier) {
                IconButton(onClick = {
                    appState.navController.navigate(NavigationScreens.Favorites.route) {
                        this.launchSingleTop = true
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null
                    )
                }
            }
        }
    ) { innerPadding ->
        //navigation content
        Navigation(
            appState = appState,
            padding = innerPadding
        )
    }
}

    // navController created in a place in the hierarchy where all composables that need it have access
@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) {
    MyFetchAppState(navController)
}
