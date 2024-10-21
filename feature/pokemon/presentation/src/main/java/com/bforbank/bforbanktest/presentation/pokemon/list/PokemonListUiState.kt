package com.bforbank.bforbanktest.presentation.pokemon.list

import com.bforbank.bforbanktest.presentation.pokemon.model.PokemonUI
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class PokemonListUiState(
    val pokemonList : ImmutableList<PokemonUI> = persistentListOf(),
    val displayErrorMessage : Boolean = false,
    val isLoading : Boolean = true,
    val isLoadingMore : Boolean = false,
    val hasErrorLoadingMore: Boolean = false,
    val searchText: String = ""
)
