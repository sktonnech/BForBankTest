package com.bforbank.bforbanktest.presentation.pokemon.details.uicomponent

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.bforbank.core.designsystem.theme.BForBankTheme
import com.bforbank.core.designsystem.utils.LightAndDarkPreviews


@Composable
fun ErrorMessage(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = "Failed to load Pokemon details",
        style = BForBankTheme.typography.bodyLarge,
        fontSize = 20.sp
    )
}

@LightAndDarkPreviews
@Composable
fun ErrorMessagePreview() {
    Surface {
        BForBankTheme {
            ErrorMessage()
        }
    }
}