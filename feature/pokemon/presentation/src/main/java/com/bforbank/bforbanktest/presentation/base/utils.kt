package com.bforbank.bforbanktest.presentation.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
inline fun <reified T : Function<*>?> rememberLambda(
    vararg keys: Any?,
    lambda: T
) = remember(*keys) {
    lambda
}