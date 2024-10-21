package com.bforbank.bforbanktest.presentation.pokemon.details

import com.bforbank.bforbanktest.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor()  : BaseViewModel<PokemonDetailsAction, PokemonDetailsUiState>(initialState = PokemonDetailsUiState())  {

    override fun handle(action: PokemonDetailsAction) {
        when(action) {
            PokemonDetailsAction.Back -> { /* do something */ }
        }
    }
}