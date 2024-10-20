package com.bforbank.bforbanktest.presentation.pokemon.navigation


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bforbank.bforbanktest.presentation.base.rememberLambda
import com.bforbank.bforbanktest.presentation.pokemon.details.PokemonDetailsScreenRoute
import com.bforbank.bforbanktest.presentation.pokemon.details.PokemonDetailsNavInfo
import com.bforbank.bforbanktest.presentation.pokemon.details.PokemonDetailsNavInfo.navigateToPokemonDetailsScreen
import com.bforbank.bforbanktest.presentation.pokemon.list.PokemonListNavInfo
import com.bforbank.bforbanktest.presentation.pokemon.list.PokemonListScreenRoute
import com.bforbank.bforbanktest.presentation.pokemon.model.PokemonUI


@Composable
fun PokemonNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PokemonListNavInfo.ROUTE,
    ) {
        pokemonDetailsNavGraph(navController = navController)
        pokemonListNavGraph(navController = navController)
    }
}


fun NavGraphBuilder.pokemonDetailsNavGraph(navController: NavController) {
    composable(route = PokemonDetailsNavInfo.ROUTE) {

        val selectedPokemon = navController.findSavedField<PokemonUI>(
            PokemonDetailsNavInfo.Args.SELECTED_POKEMON
        ) ?: run {
            Log.e("navigation", "Selected pokemon  not found. Cannot navigate to details screen")
            return@composable
        }
        PokemonDetailsScreenRoute(
            selectedPokemon = selectedPokemon,
            onBackClicked = { navController.popBackStack() }
        )
    }
}


fun NavGraphBuilder.pokemonListNavGraph(navController: NavController) {
    composable(route = PokemonListNavInfo.ROUTE) {
        PokemonListScreenRoute(
            onItemClicked = rememberLambda(navController) { pokemon ->
                navController.navigateToPokemonDetailsScreen(pokemon)
            }
        )
    }
}

private fun <T> NavController.findSavedField(field: String): T? {
    return previousBackStackEntry
        ?.savedStateHandle
        ?.get<T>(field)
}