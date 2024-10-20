package com.bforbank.bforbanktest.domain.repository

import com.bforbank.bforbanktest.domain.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getListPokemon() : Flow<List<PokemonEntity>>
}