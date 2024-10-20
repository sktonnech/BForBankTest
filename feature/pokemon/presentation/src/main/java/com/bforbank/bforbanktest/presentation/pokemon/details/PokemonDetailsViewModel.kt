package com.bforbank.bforbanktest.presentation.pokemon.details

import com.bforbank.bforbanktest.domain.usecase.ObservePokemonListUseCase
import com.bforbank.bforbanktest.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val observePokemonListUseCase : ObservePokemonListUseCase
)  : BaseViewModel<PokemonDetailsAction, PokemonDetailsUiState>(initialState = PokemonDetailsUiState())  {



    override fun handle(action: PokemonDetailsAction) {
        when(action) {
            PokemonDetailsAction.Back -> {}
        }
    }
}