package com.bforbank.bforbanktest.presentation.pokemon.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bforbank.bforbanktest.presentation.base.rememberLambda
import com.bforbank.bforbanktest.presentation.pokemon.model.PokemonUI

@Composable
internal fun PokemonListScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel(),
    onItemClicked : (PokemonUI) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PokemonListScreen(
        modifier = modifier,
        pokemonListUiState = { uiState },
        onItemClicked = onItemClicked,
        onRetryClicked = rememberLambda (viewModel){ viewModel.handle(PokemonListAction.Retry) },
        onLoadMore = rememberLambda (viewModel){ viewModel.handle(PokemonListAction.LoadMore) },
        onLoadMoreError = rememberLambda (viewModel){ viewModel.handle(PokemonListAction.ConsumeDisplayLoadingMoreError) },
        onSearchPokemon = rememberLambda (viewModel) { searchText ->
            viewModel.handle(PokemonListAction.SearchPokemon(searchText))
        }
    )
}