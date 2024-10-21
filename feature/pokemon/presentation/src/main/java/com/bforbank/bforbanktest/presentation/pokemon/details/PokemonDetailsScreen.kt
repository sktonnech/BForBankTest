package com.bforbank.bforbanktest.presentation.pokemon.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bforbank.bforbanktest.presentation.pokemon.details.uicomponent.DetailsScreen
import com.bforbank.bforbanktest.presentation.pokemon.details.uicomponent.ErrorMessage
import com.bforbank.bforbanktest.presentation.pokemon.model.PokemonUI
import com.bforbank.core.designsystem.organism.TopAppBarState
import com.bforbank.core.designsystem.template.BForBankScreenTemplate
import com.bforbank.core.designsystem.theme.BForBankTheme
import com.bforbank.core.designsystem.utils.LightAndDarkPreviews

@Composable
fun PokemonDetailsScreen(
    modifier: Modifier = Modifier,
    selectedPokemon: PokemonUI?,
    pokemonDetailsUiState: PokemonDetailsUiState,
    onBackClicked: () -> Unit
) {

    BForBankScreenTemplate(
        modifier = modifier,
        topAppBarState = TopAppBarState(onBackClicked = onBackClicked, title = "Pokemon Details"),
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues),
        ) {
            selectedPokemon?.let {
                DetailsScreen(
                    modifier = Modifier.fillMaxSize(),
                    pokemon = selectedPokemon
                )
            } ?: run {
                ErrorMessage(modifier)
            }
        }
    }
}

@LightAndDarkPreviews
@Composable
fun PokemonDetailsScreenPreview() {
    Surface {
        BForBankTheme {
            PokemonDetailsScreen(
                selectedPokemon = PokemonUI(id = 1, name = "slim", url = "url"),
                pokemonDetailsUiState = PokemonDetailsUiState(),
                onBackClicked = {}
            )
        }
    }
}

@LightAndDarkPreviews
@Composable
fun PokemonDetailsScreenErrorPreview() {
    Surface {
        BForBankTheme {
            PokemonDetailsScreen(
                selectedPokemon = null,
                pokemonDetailsUiState = PokemonDetailsUiState(),
                onBackClicked = {}
            )
        }
    }
}
