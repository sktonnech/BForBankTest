package com.bforbank.bforbanktest.data.mapper

import com.bforbank.bforbanktest.data.model.Pokemon
import com.bforbank.bforbanktest.domain.model.PokemonEntity


internal fun Pokemon.toPokemonEntity() = PokemonEntity(id = url.getIdFromUrl(), name = name, url = url)

internal fun List<Pokemon>.toPokemonEntityList(): List<PokemonEntity> {
    val pokemonEntityList = mutableListOf<PokemonEntity>()
    this.forEach { pokemon ->
        pokemonEntityList.add(pokemon.toPokemonEntity())
    }
    return pokemonEntityList
}

// because the url ws doesn't have an ID for each item :/
private fun String.getIdFromUrl() = this.substringBeforeLast("/")
    .substringAfterLast("/")
    .toInt()