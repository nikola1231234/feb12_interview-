package com.example.list.presentation.util.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.list.domain.model.Item

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    navController: NavController,
    item: Item,
    onClick: () -> Unit = {}
) {
    val focusRequester = remember {
        FocusRequester()
    }

    MyCard(
        modifier = modifier
            .focusRequester(focusRequester)
            .width(150.dp)
            .wrapContentHeight(),
        navController = navController,
        onClick = onClick
    ) {
        AsyncImage(model = item.URLImage, contentDescription = item.title)
    }
}