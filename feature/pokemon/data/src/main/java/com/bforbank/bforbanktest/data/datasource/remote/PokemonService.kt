package com.bforbank.bforbanktest.data.datasource.remote

import com.bforbank.bforbanktest.data.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface PokemonService {

    @GET("pokemon")
    suspend fun getListPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonResponse>
}