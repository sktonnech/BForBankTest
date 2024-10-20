package com.bforbank.core.designsystem.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Light",
    group = "Dark and Light",
    showBackground = true
)
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    group = "Dark and Light",
    showBackground = true
)
annotation class LightAndDarkPreviews