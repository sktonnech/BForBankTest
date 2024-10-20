package com.bforbank.bforbanktest.presentation.pokemon.list.uicomponent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.bforbank.core.designsystem.theme.BForBankTheme
import com.bforbank.core.designsystem.utils.LightAndDarkPreviews

@Composable
internal fun LoadingMessage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = modifier.size(48.dp),
            strokeWidth = 4.dp,
            strokeCap = StrokeCap.Round,
            trackColor = Color.Transparent
        )
    }
}

@LightAndDarkPreviews
@Composable
fun LoadingScreenPreview() {
    Surface {
        BForBankTheme {
            LoadingMessage()
        }
    }
}