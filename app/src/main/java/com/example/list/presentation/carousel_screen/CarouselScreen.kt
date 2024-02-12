package com.example.list.presentation.carousel_screen

import android.widget.Toast
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.tv.material3.Carousel
import androidx.tv.material3.CarouselState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import coil.compose.AsyncImage
import com.example.demoapp.util.Constants
import com.example.list.presentation.ItemsViewState
import com.example.list.presentation.util.components.ChannelItem
import com.example.list.presentation.util.components.LoadingDialog
import com.example.list.presentation.util.components.MyButtonWithText

private const val TAG = "CarouselScreen"

@Composable
internal fun CarouselScreen(
    navController: NavController,
    viewModel: CarouselViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CarouselContent(
        navController = navController,
        state = state,
        onActiveItemChanged = { index ->
            viewModel.onActiveItemChanged(index)
        }
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CarouselContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: ItemsViewState,
    onActiveItemChanged: (Int) -> Unit = {}
) {
    val context = LocalContext.current

    val carouselState = remember {
        CarouselState()
    }

    LaunchedEffect(carouselState.activeItemIndex) {
        onActiveItemChanged(carouselState.activeItemIndex)
    }

    LoadingDialog(isLoading = state.isLoading)
    Carousel(
        modifier = modifier.fillMaxSize(),
        carouselState = carouselState,
        autoScrollDurationMillis = 5000,
        contentTransformStartToEnd = ContentTransform(
            targetContentEnter = fadeIn(animationSpec = tween(500)),
            initialContentExit = fadeOut(
                tween(500)
            )
        ),
        contentTransformEndToStart = ContentTransform(
            targetContentEnter = fadeIn(animationSpec = tween(500)),
            initialContentExit = fadeOut(
                tween(500)
            )
        ),
        itemCount = state.items.count()
    ) { index ->
        val focusRequester = remember {
            FocusRequester()
        }

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = state.items[index].URLImage,
            contentDescription = state.items[index].title
        )

        Column(
            modifier = Modifier
                .width(300.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ChannelItem(
                item = state.items[index],
                navController = navController
            )

            MyButtonWithText(modifier = Modifier
                .padding(
                    bottom = 32.dp
                )
                .focusRequester(focusRequester),
                text = state.selectedItem?.title ?: Constants.NO_INFORMATION_TEXT,
                navController = navController,
                onClick = {
                    Toast.makeText(
                        context,
                        ("Display name: " + state.selectedItem?.onClick?.displayName),
                        Toast.LENGTH_SHORT
                    ).show()
                })
        }
    }

}
