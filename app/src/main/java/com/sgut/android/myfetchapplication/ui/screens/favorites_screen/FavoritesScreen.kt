package com.sgut.android.myfetchapplication.ui.screens.favorites_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sgut.android.myfetchapplication.data.db.FavoriteItem
import com.sgut.android.myfetchapplication.R.string as AppText


@Composable
fun FavoritesScreen(
    favoritesScreenViewModel: FavoritesScreenViewModel = hiltViewModel(),
) {
    val favoritesScreenUiState by favoritesScreenViewModel.favoritesScreenUiState.collectAsState(initial = FavoritesScreenUiState.NoItems)

   FavoritesContent(
       modifier = Modifier,
       favoritesScreenUiState = favoritesScreenUiState,
   )
}

@Composable
private fun FavoritesContent(
    modifier: Modifier,
    favoritesScreenUiState: FavoritesScreenUiState
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Log.d("FAVSCREEN WHEN", favoritesScreenUiState.toString())
        when(favoritesScreenUiState){
            FavoritesScreenUiState.NoItems -> NoItems()

            is FavoritesScreenUiState.Content -> FavoriteItemsList(items = favoritesScreenUiState.favItemsList)
        }
    }



}

@Composable
private fun NoItems(
) {

        Text(
            text = stringResource(AppText.no_favorites),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

}

@Composable
private fun FavoriteItemCard(
    item: FavoriteItem,
    modifier: Modifier,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp,
        backgroundColor = Color.Yellow,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier.fillMaxWidth()
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
                fontSize = 20.sp
            )
            Text(
                text = item.listId.toString(),
                color = Color.Black,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
private fun FavoriteItemsList(
    items: List<FavoriteItem>,
) {
    LazyColumn {
        items(items = items) { item ->
            Row {
                FavoriteItemCard(
                    item = item, modifier = Modifier.padding(8.dp),

                    )
            }
        }
    }
}










