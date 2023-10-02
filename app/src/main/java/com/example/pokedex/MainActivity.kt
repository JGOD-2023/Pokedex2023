package com.example.pokedex

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.pokedex.model.Pokemon
import com.example.pokedex.ui.theme.PokedexTheme
import com.example.pokedex.ui.theme.list.LocationViewModel
import com.example.pokedex.ui.theme.list.PokemonScreen
import com.example.pokedex.ui.theme.list.PokemonViewModel


class MainActivity : ComponentActivity() {
    private lateinit var locationViewModel: LocationViewModel
    private val requestLocationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                locationViewModel.initLocationClient(this)
                locationViewModel.requestLocationPermission(this)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Location()
                    Principal()
                }
            }
        }
    }
    @Composable
    fun Principal(){
        // Inicializa el ViewModel
        val viewModel: PokemonViewModel = viewModel()
        viewModel.getPokemon()
        // Pantalla principal
        PokemonScreen(viewModel = viewModel)
    }
    @Composable
    fun Location() {
        locationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)
        locationViewModel.locationLiveData.observe(this, Observer { location ->
        })

        locationViewModel.flag.observe(this, Observer { flag ->
            if (flag) {
                setContent {
                        Principal()
                }
                vibrar()
                Toast.makeText(this, "Pokemon encontrado", Toast.LENGTH_SHORT).show()
            }
        })


        if (hasLocationPermission()) {
            locationViewModel.initLocationClient(this)
            locationViewModel.requestLocationPermission(this)
        } else {
            requestLocationPermissionLauncher.launch(ACCESS_FINE_LOCATION)
        }
    }
    private fun hasLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun showMessage(message: String) {
        androidx.compose.ui.platform.ComposeView(this).apply {
            setContent {
                Principal()
            }
        }
        vibrar()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    fun vibrar() {

        // Obtén una referencia al servicio Vibrator
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        // Verifica si la versión de Android es compatible con la nueva API de Vibrator
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // Crea una vibración personalizada
            val vibrationEffect =
                VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE)

            // Vibra el teléfono durante 1 segundo
            vibrator.vibrate(vibrationEffect)
        } else {
            // Versiones anteriores a Android Oreo
            vibrator.vibrate(1000) // Vibra durante 1 segundo
        }
    }
}

@Composable
fun PokemonCard(pokemon: Pokemon?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            if (pokemon != null) {
                Image(
                    painter = rememberImagePainter(data = pokemon.sprites.front_default),
                    contentDescription = "Pokemon Image",
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

/*@Composable
@Preview
fun ScreenMain() {



    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        AsyncImage(
            model = pokemonLista[1].imageUrl,
            "Pokémon image",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(8.dp)
        )
        Text(
            "#${pokemonLista[1].id}",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(4.dp)
        )
        Text(
            pokemonLista[1].name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.fillMaxWidth())
    }
}
@Composable
fun mySpacer(size: Int) {
    Spacer(
        modifier = Modifier
            .height(size.dp)
            .width(0.dp)
    )

}*/

