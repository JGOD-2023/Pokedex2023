package com.example.pokedex.ui.theme.list

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.MainActivity
import com.example.pokedex.model.Pokemon
import com.google.android.gms.location.*

class LocationViewModel : ViewModel() {
    val locationLiveData = MutableLiveData<String>()
    val flag = MutableLiveData<Boolean>()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private var lastLocation: android.location.Location? = null

    fun initLocationClient(activity: MainActivity) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

        locationRequest = LocationRequest.create().apply {
            interval = 10000 // Intervalo de actualización de ubicación en milisegundos
            fastestInterval = 5000 // Intervalo mínimo de actualización de ubicación
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }
    fun requestLocationPermission(activity: MainActivity) {
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        } else {
            startLocationUpdates(activity)
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates(activity: MainActivity) {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }
    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult ?: return
            for (location in locationResult.locations) {
                if (lastLocation != null) {
                    val distance = location.distanceTo(lastLocation!!)
                    if (distance >= 5.0) {
                        flag.value = true
                    } else {
                        flag.value = false
                    }
                }
                lastLocation = location
                locationLiveData.value = "Latitud: ${location.latitude}, Longitud: ${location.longitude}"
            }

            }
        }
    companion object {
        const val REQUEST_LOCATION_PERMISSION = 1
    }
}
