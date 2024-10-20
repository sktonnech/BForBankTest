package com.bforbank.bforbanktest.presentation.pokemon.list

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bforbank.bforbanktest.domain.model.PokemonEntity
import com.bforbank.bforbanktest.presentation.pokemon.list.uicomponent.ErrorMessage
import com.bforbank.bforbanktest.presentation.pokemon.list.uicomponent.LoadingMessage
import com.bforbank.bforbanktest.presentation.pokemon.list.uicomponent.PokemonListItem
import com.bforbank.bforbanktest.presentation.pokemon.model.PokemonUI
import com.bforbank.core.designsystem.organism.TopAppBarState
import com.bforbank.core.designsystem.template.BForBankScreenTemplate
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun PokemonListScreen(
    modifier: Modifier,
    pokemonListUiState: () -> PokemonListUiState,
    onItemClicked: (PokemonUI) -> Unit,
    onRetryClicked: () -> Unit,
    onLoadMore: () -> Unit,
    onLoadMoreError: () -> Unit
) {

    val context = LocalContext.current

    val isLoading by remember(pokemonListUiState) { derivedStateOf { pokemonListUiState().isLoading } }
    val isLoadingMore by remember(pokemonListUiState) { derivedStateOf { pokemonListUiState().isLoadingMore } }
    val displayErrorMessage by remember(pokemonListUiState) { derivedStateOf { pokemonListUiState().displayErrorMessage } }
    val hasErrorLoadingMore by remember(pokemonListUiState) { derivedStateOf { pokemonListUiState().hasErrorLoadingMore } }

    // Show a toast if there was an error while loading more items
    LaunchedEffect(hasErrorLoadingMore) {
        if (hasErrorLoadingMore) {
            Toast.makeText(context, "Error loading more Pokémon", Toast.LENGTH_SHORT).show()
            onLoadMoreError()
        }
    }

    BForBankScreenTemplate(
        modifier = modifier,
        topAppBarState = TopAppBarState(title = "Pokemon List"),

        ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {

            if (isLoading) {
                LoadingMessage()
            } else if (displayErrorMessage) {
                ErrorMessage(modifier = Modifier, onRetryClicked = onRetryClicked)
            } else {
                PokemonList(
                    modifier = Modifier.fillMaxSize(),
                    pokemonList = pokemonListUiState().pokemonList,
                    onItemClicked = onItemClicked,
                    onLoadMore = onLoadMore,
                    isLoadingMore = isLoadingMore
                )
            }
        }
    }
}

@Composable
fun PokemonList(
    modifier: Modifier,
    pokemonList: ImmutableList<PokemonUI>,
    listState: LazyListState = rememberLazyListState(),
    isLoadingMore: Boolean,
    onItemClicked: (PokemonUI) -> Unit,
    onLoadMore: () -> Unit
) {

    val reachedBottom: Boolean by remember { derivedStateOf { listState.reachedBottom() } }

    // load more if scrolled to bottom
    LaunchedEffect(reachedBottom) {
        if (reachedBottom && !isLoadingMore) {
            println("slim loadMore")
            onLoadMore()
        }
    }

    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        items(
            items = pokemonList,
            key = { pokemon: PokemonUI -> pokemon.name },
        ) { pokemon ->
            PokemonListItem(pokemon, onItemClicked = onItemClicked)
        }
        if (isLoadingMore) {
            item {
                LoadingMessage()
            }
        }
    }
}

private fun LazyListState.reachedBottom(): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return this.lastScrolledForward
            && lastVisibleItem?.index != 0
            && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - 2
}

