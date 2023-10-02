package com.example.pokedex.api

import com.example.pokedex.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon/{id}/")
    suspend fun getPokemon(@Path("id") id: Int): Pokemon
}