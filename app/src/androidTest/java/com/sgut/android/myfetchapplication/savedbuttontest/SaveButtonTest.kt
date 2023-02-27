package com.sgut.android.myfetchapplication.savedbuttontest

import android.graphics.drawable.Icon
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.sgut.android.myfetchapplication.R
import com.sgut.android.myfetchapplication.ui.screens.main_screen.SaveItemButton
import org.junit.Rule
import org.junit.Test

class SaveButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun saveItemButtonTest() {
        composeTestRule.setContent {
            SaveItemButton(
                onClick = { /*TODO*/ },
                icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                text = { Text(if (true) stringResource(R.string.saved) else stringResource(R.string.add_to_favorites)) },
                isPressed = true
            )
        }
    }
}