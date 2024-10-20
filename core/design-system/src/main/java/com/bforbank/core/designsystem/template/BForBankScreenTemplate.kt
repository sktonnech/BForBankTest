package com.bforbank.core.designsystem.template

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bforbank.core.designsystem.organism.TopAppBarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BForBankScreenTemplate(
    modifier: Modifier,
    topAppBarState: TopAppBarState?,
    content: @Composable (paddingValues: PaddingValues) -> Unit,
) {
    Scaffold(modifier = modifier.fillMaxSize(),
        topBar = {
            topAppBarState?.let { state ->
                TopAppBar(
                    modifier = Modifier.background(Color.Red),
                    title = {
                        state.title?.let {
                            Text(text = it)
                        }
                    },
                    navigationIcon = {
                        state.onBackClicked?.let { onBackClicked ->
                            IconButton(onClick = { onBackClicked() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        }
                    }
                )
            }
        },
        content = { paddingValues ->
            content(paddingValues)
        }
    )
}