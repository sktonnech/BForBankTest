package com.bforbank.bforbanktest.presentation.pokemon.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bforbank.bforbanktest.presentation.pokemon.details.uicomponent.DetailsScreen
import com.bforbank.bforbanktest.presentation.pokemon.details.uicomponent.ErrorMessage
import com.bforbank.bforbanktest.presentation.pokemon.model.PokemonUI
import com.bforbank.core.designsystem.organism.TopAppBarState
import com.bforbank.core.designsystem.template.BForBankScreenTemplate

@Composable
fun PokemonDetailsScreen(
    modifier: Modifier = Modifier,
    selectedPokemon: PokemonUI,
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
            pokemonDetailsUiState.pokemon?.let {
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
