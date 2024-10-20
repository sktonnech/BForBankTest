package com.bforbank.bforbanktest.presentation.pokemon.details

import com.bforbank.bforbanktest.domain.model.PokemonEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class PokemonDetailsUiState(
    val pokemon : PokemonEntity? = null,
    val displayErrorMessage : Boolean = false,
    val isLoading : Boolean = true
)
