package com.example.kotlinfirstproject
import android.content.IntentSender
import android.location.Location
import android.util.Log
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationSettingsResponse



class FusedLocation(private var mCtx: MainActivity) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    fun locationServicesClient() {
        Log.d("Tag", "object : $mCtx")
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(mCtx)

        locate()
    }

    private fun locate() {

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    val lat = location.latitude
                    val long = location.longitude

                    Log.d("Tag", "Lat is $lat \n Lang is $long")
                } else {
                    Log.d("Tag", "Location is null")
                }
            }
    }

    fun createLocationRequest() {
        val locationRequest = LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest!!)

        val client: SettingsClient = LocationServices.getSettingsClient(mCtx)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener { locationSettingsResponse ->
            // All location settings are satisfied. The client can initialize
            // location requests here.
            Log.d("Tag","Task success")
        }

        task.addOnFailureListener { exception ->
            Log.d("Tag","Task fails")

            if (exception is ResolvableApiException){
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
//                     and check the result in onActivityResult().
                    exception.startResolutionForResult(mCtx, //this line of code will promt the user to open his gps
                        1)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }

//        Log.d("Tag","" +task.result?.locationSettingsStates)
    }
}