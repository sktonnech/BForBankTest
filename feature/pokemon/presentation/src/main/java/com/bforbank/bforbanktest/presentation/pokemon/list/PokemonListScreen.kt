package com.bforbank.bforbanktest.presentation.pokemon.list

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bforbank.bforbanktest.presentation.pokemon.list.uicomponent.ErrorMessage
import com.bforbank.bforbanktest.presentation.pokemon.list.uicomponent.LoadingMessage
import com.bforbank.bforbanktest.presentation.pokemon.list.uicomponent.PokemonList
import com.bforbank.bforbanktest.presentation.pokemon.model.PokemonUI
import com.bforbank.core.designsystem.organism.TopAppBarState
import com.bforbank.core.designsystem.template.BForBankScreenTemplate
import com.bforbank.core.designsystem.theme.BForBankTheme
import com.bforbank.core.designsystem.utils.LightAndDarkPreviews
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun PokemonListScreen(
    modifier: Modifier,
    pokemonListUiState: () -> PokemonListUiState,
    onItemClicked: (PokemonUI) -> Unit,
    onRetryClicked: () -> Unit,
    onLoadMore: () -> Unit,
    onLoadMoreError: () -> Unit,
    onSearchPokemon: (String) ->Unit
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
        topAppBarState = TopAppBarState(
            actions = {
                TextField(
                    value = pokemonListUiState().searchText,
                    onValueChange = {
                        onSearchPokemon(it)
                    },
                    placeholder = { Text("Search Pokémon") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),

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
                    isLoadingMore = isLoadingMore,
                    searchText =  pokemonListUiState().searchText
                )
            }
        }
    }
}

@LightAndDarkPreviews
@Composable
fun PokemonListScreenPreview() {
    Surface {
        BForBankTheme {
          PokemonListScreen(
              modifier = Modifier,
              pokemonListUiState = {
                  PokemonListUiState(
                      pokemonList = persistentListOf(
                          PokemonUI(id = 1, name = "name1", url = "url"),
                          PokemonUI(id = 2, name = "name2", url = "url"),
                          PokemonUI(id = 3, name = "name3", url = "url"),
                          PokemonUI(id = 4, name = "name4", url = "url"),
                      ),
                      isLoading = false
                  )
              },
              onItemClicked = {},
              onRetryClicked = {},
              onLoadMore = {},
              onLoadMoreError = {},
              onSearchPokemon = {}
          )
        }
    }
}

@LightAndDarkPreviews
@Composable
fun PokemonListScreenErrorPreview() {
    Surface {
        BForBankTheme {
          PokemonListScreen(
              modifier = Modifier,
              pokemonListUiState = {
                  PokemonListUiState(
                      isLoading = false,
                      displayErrorMessage = true
                  )
              },
              onItemClicked = {},
              onRetryClicked = {},
              onLoadMore = {},
              onLoadMoreError = {},
              onSearchPokemon = {}
          )
        }
    }
}


@LightAndDarkPreviews
@Composable
fun PokemonListScreenLoadMorePreview() {
    Surface {
        BForBankTheme {
            PokemonListScreen(
                modifier = Modifier,
                pokemonListUiState = {
                    PokemonListUiState(
                        pokemonList = persistentListOf(
                            PokemonUI(id = 1, name = "name1", url = "url"),
                            PokemonUI(id = 2, name = "name2", url = "url"),
                            PokemonUI(id = 3, name = "name3", url = "url"),
                            PokemonUI(id = 4, name = "name4", url = "url"),
                        ),
                        isLoading = false,
                        isLoadingMore = true
                    )
                },
                onItemClicked = {},
                onRetryClicked = {},
                onLoadMore = {},
                onLoadMoreError = {},
                onSearchPokemon = {}
            )
        }
    }
}