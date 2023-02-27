package com.sgut.android.myfetchapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.sgut.android.myfetchapplication.common_composables.MainToolBar
import com.sgut.android.myfetchapplication.common_composables.Navigation
import com.sgut.android.myfetchapplication.common_composables.NavigationScreens

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
                    // this makes only one screen even if clicked multiple times so they're not added to back stack
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
@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) {
    MyFetchAppState(navController)
}
