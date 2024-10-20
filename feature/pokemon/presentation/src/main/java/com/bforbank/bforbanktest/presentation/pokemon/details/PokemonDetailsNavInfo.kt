package com.bforbank.bforbanktest.presentation.pokemon.details

import androidx.navigation.NavController
import com.bforbank.bforbanktest.presentation.pokemon.model.PokemonUI

object PokemonDetailsNavInfo {
    const val ROUTE = "details_route"
     object Args {
         const val SELECTED_POKEMON = "selected_pokemon"
     }



    fun NavController.navigateToPokemonDetailsScreen(
        pokemonEntity: PokemonUI,
     ) {
         currentBackStackEntry?.savedStateHandle?.set(Args.SELECTED_POKEMON, pokemonEntity)
         navigate(ROUTE)
     }
}
