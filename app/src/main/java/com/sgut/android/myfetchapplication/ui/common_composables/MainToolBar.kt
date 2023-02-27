package com.sgut.android.myfetchapplication.ui.common_composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sgut.android.myfetchapplication.utils.formatTo
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainToolBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp:() -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        title = {
                Text(text = currentScreen)
                },
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior
    )

}