package com.bforbank.core.designsystem.organism

data class TopAppBarState(
    val onBackClicked: (() -> Unit)? = null,
    val title: String? = null,
)