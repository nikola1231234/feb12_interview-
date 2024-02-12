package com.example.list.presentation.list_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.items
import com.example.list.presentation.ItemsViewModel
import com.example.list.presentation.ItemsViewState
import com.example.list.presentation.util.components.ItemCard
import com.example.list.presentation.util.components.ItemDescription
import com.example.list.presentation.util.components.LoadingDialog

private const val TAG = "ListScreen"

@Composable
internal fun ListScreen(
    navController: NavController,
    viewModel: ItemsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ListContent(state = state, navController = navController)
}

@Composable
fun ListContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: ItemsViewState
) {
    LoadingDialog(isLoading = state.isLoading)

    val context = LocalContext.current

    TvLazyVerticalGrid(
        columns = TvGridCells.Fixed(1),
        modifier = modifier
            .fillMaxHeight()
            .wrapContentWidth()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 20.dp)
    ) {
        items(state.items) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ItemCard(
                    item = it,
                    navController = navController,
                    onClick = {
                        Toast.makeText(context, "Card: ${it.title} clicked!", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
                ItemDescription(
                    item = it,
                    navController = navController,
                    onClick = {
                        Toast.makeText(
                            context,
                            "Description: ${it.subtitle} clicked!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        }
    }
}
