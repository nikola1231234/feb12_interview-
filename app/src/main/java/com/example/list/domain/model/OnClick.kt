package com.example.list.domain.model

import androidx.compose.runtime.Stable

@Stable
data class OnClick(
    val BOName: String? = null,
    val URLMedias: String? = null,
    val URLPage: String? = null,
    val displayName: String? = null,
    val displayTemplate: String? = null,
    val path: String? = null
)