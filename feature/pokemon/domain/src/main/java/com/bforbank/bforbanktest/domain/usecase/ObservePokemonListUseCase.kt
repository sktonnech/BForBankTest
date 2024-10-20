package com.bforbank.bforbanktest.domain.usecase

import com.bforbank.bforbanktest.domain.model.PokemonEntity
import com.bforbank.bforbanktest.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObservePokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke() : Flow<List<PokemonEntity>> = pokemonRepository.getListPokemon()
}