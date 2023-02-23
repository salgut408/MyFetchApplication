package com.sgut.android.myfetchapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sgut.android.myfetchapplication.ui.theme.MyFetchApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFetchApplicationTheme {
                // A surface container using the 'background' color from the theme
                MyFetchApp()

            }
        }
    }
}


