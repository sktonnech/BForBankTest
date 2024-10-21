package com.bforbank.bforbanktest.domain.usecase

import app.cash.turbine.test
import com.bforbank.bforbanktest.domain.repository.PokemonRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ObservePokemonListUseCaseTest {

    private var pokemonRepository: PokemonRepository = mockk()

    private lateinit var observePokemonListUseCase : ObservePokemonListUseCase

    @Before
    fun before() {
        observePokemonListUseCase = ObservePokemonListUseCase(pokemonRepository)
    }

    @Test
    fun `given empty pokemon list, then emit empty list`(){
        runTest {
            //Given
            coEvery { pokemonRepository.getListPokemon() } returns flow { emit(emptyList()) }

            //When
            val result = observePokemonListUseCase()

            //Then
            result.test {
                val panelAlert = awaitItem()
                assertEquals(panelAlert.size, 0)
                awaitComplete()
            }
            coVerify (exactly = 1) {
                pokemonRepository.getListPokemon()
            }
        }
    }

    @Test
    fun `given error, then emit empty list`(){
        runTest {
            val givenErrorMessage = "exceptionMessage"

            //Given
            coEvery { pokemonRepository.getListPokemon() } returns flow { emit(throw Exception(givenErrorMessage)) }

            //When
            val result = observePokemonListUseCase()

            //Then
            result.test {
                val error = awaitError() // Turbine helper method to capture the error
                assertEquals(givenErrorMessage, error.message)
            }
            coVerify (exactly = 1) {
                pokemonRepository.getListPokemon()
            }
        }
    }
}