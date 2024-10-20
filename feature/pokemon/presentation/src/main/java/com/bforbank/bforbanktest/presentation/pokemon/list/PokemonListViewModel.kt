package com.bforbank.bforbanktest.presentation.pokemon.list

import com.bforbank.bforbanktest.domain.usecase.ObservePokemonListUseCase
import com.bforbank.bforbanktest.presentation.base.BaseViewModel
import com.bforbank.bforbanktest.presentation.pokemon.model.toPokemonUIList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
internal class PokemonListViewModel @Inject constructor(
    private val observePokemonListUseCase: ObservePokemonListUseCase
) : BaseViewModel<PokemonListAction, PokemonListUiState>(initialState = PokemonListUiState()) {

    init {
        fetchPokemon()
    }

    override fun handle(action: PokemonListAction) {
        when (action) {
            is PokemonListAction.Retry -> fetchPokemon()
            is PokemonListAction.LoadMore -> fetchPokemon(true)
            is PokemonListAction.SelectPokemon -> {}
            is PokemonListAction.ConsumeDisplayLoadingMoreError -> {
                launch {
                    updateState {
                        copy(hasErrorLoadingMore = false)
                    }
                }
            }
        }
    }

    private fun fetchPokemon(isLoadingMore: Boolean = false) {
        launch {
            updateState {
                copy(
                    isLoading = !isLoadingMore,
                    isLoadingMore = isLoadingMore,
                    displayErrorMessage = false,
                )
            }
            observePokemonListUseCase().catch {
                updateState {
                    copy(
                        isLoading = false,
                        isLoadingMore = false,
                        displayErrorMessage = !isLoadingMore,
                        hasErrorLoadingMore = isLoadingMore
                    )
                }
            }.collect { list ->
                updateState {
                    val newPokemonList  = if (isLoadingMore) pokemonList + list.toPokemonUIList() else list.toPokemonUIList()
                    copy(
                        pokemonList = newPokemonList.toPersistentList(),
                        isLoading = false,
                        displayErrorMessage = false,
                        isLoadingMore = false
                    )
                }
            }
        }
    }
}