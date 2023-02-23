package com.sgut.android.myfetchapplication.main_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sgut.android.myfetchapplication.data.domain_models.ItemDomainModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue



@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
) {
   val uiState by mainScreenViewModel.mainScreenUiState.collectAsState()

    ItemsList(
        uiState = uiState,
        navController = navController
    )


}


@Composable
fun ItemCard(
    item: ItemDomainModel,
    modifier: Modifier,
    navController: NavController
    ) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp,
        backgroundColor = Color.LightGray,
        modifier = modifier
            .fillMaxWidth()
            .clickable { }
    ) {
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
      }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemsList(
    uiState: MainScreenUiState,
    navController: NavController
) {
    LazyColumn {
        items(items = uiState.currentItems) { item ->
            Row(modifier = Modifier.animateItemPlacement()) {
                ItemCard(
                    item = item, modifier = Modifier.padding(8.dp),
                    navController = navController
                )
            }
        }
    }
}
















