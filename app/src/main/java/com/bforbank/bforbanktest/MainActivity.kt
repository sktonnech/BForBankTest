package com.bforbank.bforbanktest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bforbank.bforbanktest.presentation.pokemon.navigation.PokemonNavHost
import com.bforbank.core.designsystem.theme.BForBankTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint(ComponentActivity::class)
class MainActivity : Hilt_MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BForBankTheme {
                PokemonNavHost()
            }
        }
    }
}

