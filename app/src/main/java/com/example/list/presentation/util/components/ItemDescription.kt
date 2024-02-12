package com.example.list.presentation.util.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.NativeKeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.example.demoapp.ui.theme.CardBackgroundFocused
import com.example.demoapp.ui.theme.CardBackgroundNotFocused
import com.example.demoapp.ui.theme.DescriptionTextFocused
import com.example.demoapp.ui.theme.DescriptionTextNotFocused
import com.example.demoapp.ui.theme.TitleTextFocused
import com.example.demoapp.ui.theme.TitleTextNotFocused
import com.example.list.domain.model.Item

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ItemDescription(
    modifier: Modifier = Modifier,
    navController: NavController,
    item: Item,
    onClick: () -> Unit = {}
) {
    val titleTextColor = remember {
        mutableStateOf(TitleTextNotFocused)
    }
    val descriptionTextColor = remember {
        mutableStateOf(DescriptionTextNotFocused)
    }
    val backgroundColor = remember {
        mutableStateOf(CardBackgroundNotFocused)
    }
    Card(
        modifier = modifier
            .padding(15.dp)
            .onKeyEvent {
                if (it.nativeKeyEvent.action == NativeKeyEvent.ACTION_UP) {
                    when (it.nativeKeyEvent.keyCode) {
                        NativeKeyEvent.KEYCODE_BACK, NativeKeyEvent.KEYCODE_DEL, NativeKeyEvent.KEYCODE_ESCAPE -> {
                            navController.popBackStack()
                        }
                    }
                }
                return@onKeyEvent false
            }
            .fillMaxWidth(0.4f)
            .onFocusChanged {
                if (it.hasFocus) {
                    backgroundColor.value = CardBackgroundFocused
                    titleTextColor.value = TitleTextFocused
                    descriptionTextColor.value = DescriptionTextFocused
                } else {
                    backgroundColor.value = CardBackgroundNotFocused
                    titleTextColor.value = TitleTextNotFocused
                    descriptionTextColor.value = DescriptionTextNotFocused
                }
            },
        colors = CardDefaults.colors(
            containerColor = backgroundColor.value
        ),
        border = CardDefaults.border(
            focusedBorder = Border(
                BorderStroke(2.dp, Color.White)
            )
        ),
        onClick = onClick,
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = item.title!!,
                fontWeight = FontWeight.ExtraBold,
                color = titleTextColor.value,
                fontSize = 16.sp
            )
            HorizontalDivider(
                modifier = Modifier.padding(top = 1.dp, bottom = 5.dp),
                thickness = 2.dp,
                color = descriptionTextColor.value
            )
            Text(
                text = item.subtitle!!,
                fontWeight = FontWeight.Medium,
                color = descriptionTextColor.value,
                fontStyle = FontStyle.Italic,
                fontSize = 12.sp
            )
        }
    }
}