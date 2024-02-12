package com.example.list.presentation.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.demoapp.navigation.DestinationScreen
import com.example.demoapp.navigation.navigateTo
import com.example.list.presentation.util.components.MyButtonWithText

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    navController: NavController
) {
    val focusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MyButtonWithText(
                modifier = Modifier.focusRequester(
                    focusRequester
                ),
                text = DestinationScreen.ListScreen.route,
                navController = navController
            ) {
                navigateTo(
                    navController, DestinationScreen.ListScreen
                )
            }
            MyButtonWithText(
                text = DestinationScreen.CarouselScreen.route,
                navController = navController,
            ) {
                navigateTo(
                    navController, DestinationScreen.CarouselScreen
                )
            }
        }
    }
}