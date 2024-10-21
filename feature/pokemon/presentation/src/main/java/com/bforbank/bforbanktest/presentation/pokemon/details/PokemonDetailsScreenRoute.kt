package com.bforbank.bforbanktest.presentation.pokemon.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bforbank.bforbanktest.presentation.pokemon.model.PokemonUI

@Composable
fun PokemonDetailsScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: PokemonDetailsViewModel = hiltViewModel(),
    selectedPokemon : PokemonUI?,
    onBackClicked: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PokemonDetailsScreen(
        modifier = modifier,
        selectedPokemon = selectedPokemon,
        pokemonDetailsUiState = uiState,
        onBackClicked = onBackClicked
    )
}