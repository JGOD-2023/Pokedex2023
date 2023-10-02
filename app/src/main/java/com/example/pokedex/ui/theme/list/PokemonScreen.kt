package com.example.pokedex.ui.theme.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen(viewModel: PokemonViewModel) {
    val pokemon by viewModel.pokemon.observeAsState()
    val idState = remember { mutableStateOf((0..1000).random()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        /*OutlinedTextField(
            value = idState.value.toString(),
            onValueChange = {
                idState.value = it.toIntOrNull() ?: 1
            },
            label = { Text("ID del Pokémon") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )*/
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.getPokemon()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Buscar Pokémon")
        }
        pokemon?.let {
            Text("Nombre: ${it.name}")
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = rememberImagePainter(it.sprites.front_default),
                contentDescription = "Imagen del Pokémon",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}
