package com.bforbank.bforbanktest.data.repository

import com.bforbank.bforbanktest.data.datasource.remote.PokemonService
import com.bforbank.bforbanktest.data.mapper.toPokemonEntityList
import com.bforbank.bforbanktest.domain.model.PokemonEntity
import com.bforbank.bforbanktest.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

internal class PokemonRepositoryImpl @Inject constructor(private val pokemonService: PokemonService) :
    PokemonRepository {
    private var pageNumber: Int = 0
    private val limit: Int = 10 //
    private var offset: Int = 0
    override suspend fun getListPokemon(): Flow<List<PokemonEntity>> {
        pageNumber += 1 // pass to next page
        offset = limit * (pageNumber - 1) // upgrade offset regards to the number of page already downloaded

        return flow {
            try {
                val response = pokemonService.getListPokemon(limit = 10, offset = offset)
                emit(
                    response.body()?.results?.toPokemonEntityList()
                        ?: throw IOException("Network error: ")
                )
            } catch (exception: IOException) {
                pageNumber -= 1
                throw IOException("Network error: ${exception.message}")
            }
        }
    }
}

