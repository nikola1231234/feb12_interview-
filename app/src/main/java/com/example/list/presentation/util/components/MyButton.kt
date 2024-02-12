package com.example.list.presentation.util.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.NativeKeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.navigation.NavController
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.example.demoapp.ui.theme.ButtonBackgroundFocused
import com.example.demoapp.ui.theme.ButtonBackgroundNotFocused
import com.example.demoapp.ui.theme.TitleTextFocused
import com.example.demoapp.ui.theme.TitleTextNotFocused

@Composable
fun MyButtonWithText(
    modifier: Modifier = Modifier,
    text: String,
    navController: NavController,
    onClick: () -> Unit
) {

    val titleTextColor = remember {
        mutableStateOf(TitleTextNotFocused)
    }
    MyButton(
        modifier = modifier.onFocusChanged {
            if (it.hasFocus) {
                titleTextColor.value = TitleTextFocused
            } else {
                titleTextColor.value = TitleTextNotFocused
            }
        },
        navController = navController,
        onClick = onClick
    ) {
        Text(text = text, color = titleTextColor.value)
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MyButton(
    modifier: Modifier = Modifier,
    navController: NavController,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier
            .onKeyEvent {
                if (it.nativeKeyEvent.action == NativeKeyEvent.ACTION_UP) {
                    when (it.nativeKeyEvent.keyCode) {
                        NativeKeyEvent.KEYCODE_BACK, NativeKeyEvent.KEYCODE_DEL, NativeKeyEvent.KEYCODE_ESCAPE -> {
                            navController.popBackStack()
                        }
                    }
                }
                return@onKeyEvent false
            },
        onClick = onClick,
        colors = ButtonDefaults.colors(
            focusedContainerColor = ButtonBackgroundFocused,
            containerColor = ButtonBackgroundNotFocused
        ),
        content = content
    )
}