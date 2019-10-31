package com.example.kotlinfirstproject

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.content.IntentSender
import android.location.LocationManager
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*


class MainActivity : AppCompatActivity() {

    private var mGoogleApiClient: GoogleApiClient? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun startService(view: View) {
        val input = edit_text.text

        Log.d("TAG", "this is first time I am printing something on kotlin$input")


        val serviceIntent = Intent(this, MyService::class.java)
        serviceIntent.putExtra("inputExtr", input.toString())
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    fun stopService(view: View) {
        serviceIntent = Intent(this, MyService::class.java)
        stopService(serviceIntent)
    }

    private var serviceIntent: Intent? = null
    fun checkpermission(view: View) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("TAG", "No permission granted")

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                0
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val lm = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (!gpsEnabled) {
            Log.d("Tag", "onRequestPermissionsResult :$mGoogleApiClient")

            if (mGoogleApiClient == null) {
                Log.d("Tag", "onRequestPermissionsResult :$mGoogleApiClient")
                mGoogleApiClient =
                    GoogleApiClient.Builder(this@MainActivity).addApi(LocationServices.API)
                        .addConnectionCallbacks(object : GoogleApiClient.ConnectionCallbacks {
                            override fun onConnected(p0: Bundle?) {
                                Log.d("TAG", "onConnected")

                            }

                            override fun onConnectionSuspended(p0: Int) {
                                Log.d("TAG", "onConnectionSuspended")

                            }
//            var intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//            startActivity(intent)
                        }
                        ).addOnConnectionFailedListener { Log.d("TAG", "onConnectionFailed") }
                        .build()
                Log.d("Tag", "onRequestPermissionsResult :$mGoogleApiClient")
                mGoogleApiClient?.connect()

                val locationRequest = LocationRequest.create()
                locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                locationRequest.interval = (30 * 1000).toLong()
                locationRequest.fastestInterval = (5 * 1000).toLong()
                val builder = LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest)

                builder.setAlwaysShow(true)

                //Depricated SettingsApi must be removed
                val result = LocationServices.SettingsApi.checkLocationSettings(
                    mGoogleApiClient,
                    builder.build()

                )

                result.setResultCallback { p0 ->
                    Log.d("REs", "" + result)
                    val status = p0.status
                    Log.d("REs", "" + status)

                    when (status.statusCode) {

                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                            Log.d("REs", "" + status)
                            try {
                                status.startResolutionForResult(
                                    this@MainActivity,
                                    1
                                )
                            } catch (e: IntentSender.SendIntentException) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }
        }
    }
}