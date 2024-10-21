package com.bforbank.bforbanktest.presentation

import app.cash.turbine.test
import com.bforbank.bforbanktest.domain.model.PokemonEntity
import com.bforbank.bforbanktest.domain.usecase.ObservePokemonListUseCase
import com.bforbank.bforbanktest.presentation.pokemon.list.PokemonListViewModel
import com.bforbank.bforbanktest.presentation.pokemon.model.PokemonUI
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class PokemonListViewModelTest {
    private val observePokemonListUseCase: ObservePokemonListUseCase = mockk()
    private lateinit var viewModel: PokemonListViewModel

    @Before
    fun before() {
        viewModel = PokemonListViewModel(observePokemonListUseCase)
    }

    @Test
    fun `given empty list, then emit state with empty list`() {
        CoroutineScope(Dispatchers.Default).launch {
            // MutableSharedFlow to emit outside flow block
            val pokemonFlow =
                MutableSharedFlow<List<PokemonEntity>>()

            // Given:
            coEvery { observePokemonListUseCase() } returns pokemonFlow

            // when
            viewModel.fetchPokemon()

            // Then
            viewModel.uiState.test {
                val loadingState = awaitItem()
                assertEquals(false, loadingState.displayErrorMessage)
                assertEquals(true, loadingState.isLoading)

                // When emitting an empty list to the flow
                pokemonFlow.emit(listOf())

                val loadedState = awaitItem()
                assertEquals(false, loadedState.isLoading)
                assertEquals(false, loadedState.displayErrorMessage)
                assertEquals(
                    emptyList<PokemonUI>(),
                    loadedState.pokemonList
                )
                awaitComplete()
            }
        }
    }

    @Test
    fun `given exception load, then emit Error`() {
        CoroutineScope(Dispatchers.Default).launch {
            val pokemonFlow =
                MutableSharedFlow<List<PokemonEntity>>()

            // Given:
            coEvery { observePokemonListUseCase() } returns pokemonFlow

            // when
            viewModel.fetchPokemon()

            // Then
            viewModel.uiState.test {
                val loadingState = awaitItem()
                assertEquals(true, loadingState.isLoading)
                assertEquals(false, loadingState.displayErrorMessage)

                // When emitting an empty list to the flow
                pokemonFlow.emit(throw Exception())

                val loadedState = awaitItem()
                assertEquals(false, loadedState.isLoading)
                assertEquals(true, loadedState.displayErrorMessage)
                assertEquals(
                    emptyList<PokemonUI>(),
                    loadedState.pokemonList
                )
                awaitComplete()
            }
        }
    }

    @Test
    fun `given list of pokemon, then emit list Error`() {
        CoroutineScope(Dispatchers.Default).launch {
            // MutableSharedFlow to emit outside flow block
            val pokemonFlow =
                MutableSharedFlow<List<PokemonEntity>>()

            // Given:
            coEvery { observePokemonListUseCase() } returns pokemonFlow

            // when
            viewModel.fetchPokemon()

            // Then
            viewModel.uiState.test {
                val loadingState = awaitItem()
                assertEquals(true, loadingState.isLoading)
                assertEquals(false, loadingState.displayErrorMessage)

                // When emitting an empty list to the flow
                pokemonFlow.emit(
                    listOf(
                        PokemonEntity(id = 1, name = "1", url = "url1"),
                        PokemonEntity(id = 2, name = "2", url = "url2"),
                        PokemonEntity(id = 3, name = "3", url = "url3"),
                    )
                )

                val loadedState = awaitItem()
                assertEquals(false, loadedState.isLoading)
                assertEquals(false, loadedState.displayErrorMessage)
                assertEquals(
                    listOf(
                        PokemonUI(id = 1, name = "1", url = "url1"),
                        PokemonUI(id = 2, name = "2", url = "url2"),
                        PokemonUI(id = 3, name = "3", url = "url3"),
                    ),
                    loadedState.pokemonList
                )
                awaitComplete()
            }
        }
    }

    @Test
    fun `given search text, then emit filtered list`() {
        CoroutineScope(Dispatchers.Default).launch {
            // MutableSharedFlow to emit outside flow block
            val pokemonFlow =
                MutableSharedFlow<List<PokemonEntity>>()

            // Given:
            coEvery { observePokemonListUseCase() } returns pokemonFlow

            // when
            viewModel.fetchPokemon()

            // Then
            viewModel.uiState.test {
                val loadingState = awaitItem()
                assertEquals(true, loadingState.isLoading)
                assertEquals(false, loadingState.displayErrorMessage)

                // When emitting an empty list to the flow
                pokemonFlow.emit(
                    listOf(
                        PokemonEntity(id = 1, name = "1", url = "url1"),
                        PokemonEntity(id = 2, name = "2", url = "url2"),
                        PokemonEntity(id = 3, name = "3", url = "url3"),
                    )
                )

                awaitItem()

                viewModel.onSearchQueryChanged("2")

                val stateWithFilteredResult = awaitItem()
                assertEquals(
                    listOf(
                        PokemonUI(id = 2, name = "2", url = "url2"),
                    ),
                    stateWithFilteredResult.pokemonList
                )
                awaitComplete()
            }
        }
    }
}