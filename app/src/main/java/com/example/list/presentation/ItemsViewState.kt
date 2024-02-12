package com.example.list.presentation

import androidx.compose.runtime.Stable
import com.example.list.domain.model.Item

@Stable
data class ItemsViewState(
    val isLoading: Boolean = true,
    val items: List<Item> = emptyList(),
    val selectedItem: Item? = null
)
