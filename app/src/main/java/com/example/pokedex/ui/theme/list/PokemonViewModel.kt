package com.example.pokedex.ui.theme.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.api.PokemonApiService
import com.example.pokedex.model.Pokemon
import com.example.pokedex.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonViewModel : ViewModel() {
    private val pokemonService = RetrofitClient.instance

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> = _pokemon

    fun getPokemon() {
        viewModelScope.launch {
            try {
                val response = pokemonService.getPokemon(random())
                _pokemon.value = response
            } catch (e: Exception) {
                // Manejar errores
            }
        }
    }

    fun random(): Int {
        val position = (0..1000).random()
        return position
    }
}