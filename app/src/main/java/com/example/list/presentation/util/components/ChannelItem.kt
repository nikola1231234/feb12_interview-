package com.example.list.presentation.util.components

import android.widget.Toast
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.list.domain.model.Item

private const val TAG = "ChannelItem"

@Composable
fun ChannelItem(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    item: Item
) {
    val context = LocalContext.current
    item.URLLogoChannel?.let {
        MyCard(
            modifier = modifier.height(150.dp),
            navController = navController,
            onClick = {
                Toast.makeText(
                    context,
                    "Channel for title: ${item.title} clicked.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ) {
            AsyncImage(model = it, contentDescription = item.title)
        }

    }
}