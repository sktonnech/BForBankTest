package com.bforbank.bforbanktest.data.di

import com.bforbank.bforbanktest.data.repository.PokemonRepositoryImpl
import com.bforbank.bforbanktest.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Provides
    fun provideUserRepository(
        pokemonRepository: PokemonRepositoryImpl
    ): PokemonRepository {
        return pokemonRepository
    }
}