package com.bforbank.bforbanktest.presentation

import app.cash.turbine.test
import com.bforbank.bforbanktest.domain.usecase.ObservePokemonListUseCase
import com.bforbank.bforbanktest.presentation.pokemon.list.PokemonListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
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
    fun `given nothing, then emit loading`() {
        runTest {
            //Given

            //When init viewModel

            //Then
            viewModel.uiState.test {
                val uiState = awaitItem()
                assertEquals(true, uiState.isLoading)
                assertEquals(0, uiState.pokemonList.size)
            }

        }
    }

    @Test
    fun `given error loading, then display error`() {
        runTest {
            //Given
            coEvery { observePokemonListUseCase() } returns flow { throw Exception("ex") }

            //When init viewModel
            viewModel.fetchPokemon()

            //Then
            viewModel.uiState.test {
                val errorState = awaitItem()
                assertEquals(false, errorState.displayErrorMessage)
                assertEquals(true, errorState.isLoading)


                val errorState2 = awaitItem()
                assertEquals(false, errorState2.isLoading)
                awaitComplete()
            }
        }
    }
}