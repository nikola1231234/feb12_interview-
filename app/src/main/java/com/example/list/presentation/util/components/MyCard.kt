package com.example.list.presentation.util.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.NativeKeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MyCard(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    onFocusChanged: (Boolean) -> Unit = {},
    onClick: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .padding(15.dp)
            .onKeyEvent {
                if (it.nativeKeyEvent.action == NativeKeyEvent.ACTION_UP) {
                    when (it.nativeKeyEvent.keyCode) {
                        NativeKeyEvent.KEYCODE_BACK, NativeKeyEvent.KEYCODE_DEL, NativeKeyEvent.KEYCODE_ESCAPE -> {
                            navController?.popBackStack()
                        }
                    }
                }
                return@onKeyEvent false
            }
            .onFocusChanged { onFocusChanged(it.hasFocus) },
        onClick = onClick,
        border = CardDefaults.border(
            focusedBorder = Border(
                BorderStroke(2.dp, Color.White)
            )
        ),
        content = content
    )
}