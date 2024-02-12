package com.example.list.domain.model

import androidx.compose.runtime.Stable

@Stable
data class Item(
    val type: String? = null,
    val contentID: String,
    val title: String? = null,
    val subtitle: String? = null,
    val URLImage: String,
    val onClick: OnClick? = null,
    val csa: Int? = null,
    val URLLogoChannel: String? = null,
    val thirdTitle: String? = null,
    val isSelected: Boolean = false
)