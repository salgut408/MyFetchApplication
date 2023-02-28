package com.sgut.android.myfetchapplication.ui.screens.main_screen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sgut.android.myfetchapplication.domain.domain_models.ItemDomainModel
import java.util.*
import com.sgut.android.myfetchapplication.R.string as AppText


@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
) {
    val mainScreenUiState by mainScreenViewModel.mainScreenUiState.collectAsState(initial = MainScreenUiState())

    MainScreenContent(
        modifier = Modifier,
        uiState = mainScreenUiState,
        onAddClick = {item: ItemDomainModel -> mainScreenViewModel.onAddToFavoritesClick(item)},
        onRemoveClick = {item: ItemDomainModel -> mainScreenViewModel.onRemovefromFavoritesClick(item)}
    )
}

@Composable
private fun MainScreenContent(
    modifier: Modifier,
    uiState: MainScreenUiState,
    onRemoveClick: (ItemDomainModel) -> Unit,
    onAddClick: (ItemDomainModel) -> Unit,
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

       if(uiState.isLoading){
           Text(text = stringResource(id = AppText.loading), style = MaterialTheme.typography.body1)
       } else {
           ItemsList(
               uiState = uiState,
               onRemoveClick = onRemoveClick,
               onAddClick = onAddClick
           )
       }


    }
}


@Composable
private fun ItemCard(
    modifier: Modifier,
    item: ItemDomainModel,
    onRemoveClick: (ItemDomainModel) -> Unit,
    onAddClick: (ItemDomainModel) -> Unit,

    ) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp,
        backgroundColor = Color.LightGray,
        modifier = modifier
            .fillMaxWidth()
    ) {
        var isPressed by rememberSaveable { mutableStateOf(false) }
        val context = LocalContext.current
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                text = item.name ?: "",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Text(
                text = item.id.toString(),
                color = Color.Black,
                fontSize = 12.sp
            )

            Text(
                text = item.listId.toString(),
                color = Color.Black,
                fontSize = 12.sp
            )
            SaveItemButton(
                onClick = {
                    if (isPressed) {
                        onRemoveClick(item)
                        Toast.makeText(context, AppText.removed, Toast.LENGTH_SHORT).show()
                    } else {
                        onAddClick(item)
                        Toast.makeText(context, AppText.saved, Toast.LENGTH_SHORT).show()
                    }
                    isPressed = !isPressed
                },
                text = { Text(if (isPressed) stringResource(AppText.saved) else stringResource(AppText.add_to_favorites)) },
                icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                isPressed = isPressed
            )
        }
    }
}


@Composable
private fun ItemsList(
    uiState: MainScreenUiState,
    onRemoveClick: (ItemDomainModel) -> Unit,
    onAddClick: (ItemDomainModel) -> Unit,
    ) {
    LazyColumn {
        items(
            items = uiState.currentItems,
          // add key to limit recompositions
        ) { item ->
            Row {
                ItemCard(
                    item = item,
                    modifier = Modifier.padding(8.dp),
                    onAddClick = onAddClick,
                    onRemoveClick = onRemoveClick
                )
            }
        }
    }
}


@Composable
private fun SaveItemButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isPressed: Boolean,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        AnimatedVisibility(visible = isPressed) {
            if (isPressed) {
                Row {
                    icon()
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                }
            }
        }
        text()
    }
}















