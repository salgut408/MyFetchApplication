package com.sgut.android.myfetchapplication

import androidx.navigation.NavHostController

class MyFetchAppState(
    val navController: NavHostController,
) {
    fun navigate(route:String) {
        navController.navigate(route) {launchSingleTop = true}
    }
}