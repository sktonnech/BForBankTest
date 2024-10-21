package com.bforbank.bforbanktest.presentation.pokemon.list

import com.bforbank.bforbanktest.domain.usecase.ObservePokemonListUseCase
import com.bforbank.bforbanktest.presentation.base.BaseViewModel
import com.bforbank.bforbanktest.presentation.pokemon.model.PokemonUI
import com.bforbank.bforbanktest.presentation.pokemon.model.toPokemonUIList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
internal class PokemonListViewModel @Inject constructor(
    private val observePokemonListUseCase: ObservePokemonListUseCase
) : BaseViewModel<PokemonListAction, PokemonListUiState>(initialState = PokemonListUiState()) {

    // contain all loaded Pokemon without any filter
    private var allPokemonList: List<PokemonUI> = emptyList()

    init {
        fetchPokemon()
        observeSearchResult()
    }

    override fun handle(action: PokemonListAction) {
        when (action) {
            is PokemonListAction.Retry -> fetchPokemon()
            is PokemonListAction.LoadMore -> fetchPokemon(isLoadingMore = true)
            is PokemonListAction.SelectPokemon -> { }
            is PokemonListAction.ConsumeDisplayLoadingMoreError -> {
                launch {
                    updateState { copy(hasErrorLoadingMore = false) }
                }
            }
            is PokemonListAction.SearchPokemon -> onSearchQueryChanged(action.name)
        }
    }

    //
    fun onSearchQueryChanged(textSearch: String) {
        launch {
            updateState {
                copy(searchText = textSearch)
            }
        }
    }

    //observe changes on filter and update state according to it
    private fun observeSearchResult() {
        launch {
            uiState.map { it.searchText }
                .distinctUntilChanged()
                .debounce(300L) // debounce of 300L before starting search
                .collect { query ->
                    updateState {
                        val filteredList = allPokemonList.filter(query).toPersistentList()
                        copy(pokemonList = filteredList)
                    }
                }
        }
    }

    fun fetchPokemon(isLoadingMore: Boolean = false) {
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
                    allPokemonList =
                        if (isLoadingMore) allPokemonList + list.toPokemonUIList() else list.toPokemonUIList()

                    val filteredList = if (uiState.value.searchText.isEmpty()) {
                        allPokemonList
                    } else {
                        pokemonList + list.toPokemonUIList().filter(uiState.value.searchText)
                    }
                    copy(
                        pokemonList = filteredList.toPersistentList(),
                        isLoading = false,
                        displayErrorMessage = false,
                        isLoadingMore = false
                    )
                }
            }
        }
    }
}

fun List<PokemonUI>.filter(searchText: String): List<PokemonUI> =
    this.filter { it.name.contains(searchText, ignoreCase = true) }