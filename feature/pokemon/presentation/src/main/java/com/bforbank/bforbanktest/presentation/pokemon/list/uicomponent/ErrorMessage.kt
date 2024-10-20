package com.bforbank.bforbanktest.presentation.pokemon.list.uicomponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bforbank.core.designsystem.theme.BForBankTheme
import com.bforbank.core.designsystem.utils.LightAndDarkPreviews

@Composable
internal fun ErrorMessage(modifier: Modifier, onRetryClicked: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = modifier,
            text = "Failed to load Pokemon list",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = onRetryClicked) {
            Text(
                modifier = modifier,
                text = "Retry",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp
            )
        }
    }
}

@LightAndDarkPreviews
@Composable
fun ErrorMessageScreenScreenPreview() {
    Surface {
        BForBankTheme {
            ErrorMessage(Modifier, onRetryClicked = {})
        }
    }
}