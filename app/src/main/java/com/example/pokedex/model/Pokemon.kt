package com.example.pokedex.model

data class Pokemon(
    val id: Int,
    val name: String,
    val sprites: PokemonSprites
)

data class PokemonSprites(
    val front_default: String
)