package com.sgut.android.myfetchapplication.main_screen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sgut.android.myfetchapplication.data.domain_models.ItemDomainModel
import com.sgut.android.myfetchapplication.R.string as AppText


@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
) {
   val uiState by mainScreenViewModel.mainScreenUiState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        ItemsList(
            uiState = uiState,
            mainScreenViewModel = mainScreenViewModel
        )
    }




}


@Composable
fun ItemCard(
    item: ItemDomainModel,
    modifier: Modifier,
    mainScreenViewModel: MainScreenViewModel
    ) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp,
        backgroundColor = Color.LightGray,
        modifier = modifier
            .fillMaxWidth()
    ) {
        var isPressed by remember { mutableStateOf(false) }
        val context = LocalContext.current
      Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceEvenly,
          modifier = Modifier.fillMaxWidth()
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
                 if(isPressed) {
                     mainScreenViewModel.onRemovefromFavoritesClick(item)
                     Toast.makeText(context, AppText.remove, Toast.LENGTH_SHORT).show()
                 } else {
                     mainScreenViewModel.onAddToFavoritesClick(item)
                     Toast.makeText(context, AppText.saved, Toast.LENGTH_SHORT).show()
                 }
                 when (isPressed) {
                     true -> isPressed = false
                     false -> isPressed = true
                 }
                       },
             text = { Text(if (isPressed) stringResource(AppText.saved) else stringResource(AppText.add_to_favorites)) },
             icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
             isPressed = isPressed
         )
          
      }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemsList(
    uiState: MainScreenUiState,
    mainScreenViewModel: MainScreenViewModel
) {
    LazyColumn {
        items(items = uiState.currentItems) { item ->
            Row(modifier = Modifier.animateItemPlacement()) {
                ItemCard(
                    item = item, modifier = Modifier.padding(8.dp),
                    mainScreenViewModel = mainScreenViewModel

                )
            }
        }
    }
}




@Composable
fun SaveItemButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isPressed: Boolean
) {
   Button(
       onClick = onClick,
       modifier = modifier,
       interactionSource = remember { MutableInteractionSource() }
   ) {
       AnimatedVisibility(visible = isPressed) {
           if (isPressed){
               Row() {
                   icon()
                   Spacer(Modifier.size(ButtonDefaults.IconSpacing))
               }
           }
       }
       text()
   }
}















