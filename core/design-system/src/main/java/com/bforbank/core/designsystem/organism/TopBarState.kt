package com.bforbank.core.designsystem.organism

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

data class TopAppBarState(
    val onBackClicked: (() -> Unit)? = null,
    val title: String? = null,
    val actions : @Composable (RowScope.() -> Unit)? = null
)