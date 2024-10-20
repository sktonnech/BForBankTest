package com.bforbank.bforbanktest.presentation.pokemon.list


internal sealed class PokemonListAction {
    data class SelectPokemon(val id : Int) : PokemonListAction()
    data object Retry : PokemonListAction()
    data object LoadMore : PokemonListAction()
    data object ConsumeDisplayLoadingMoreError : PokemonListAction()
}