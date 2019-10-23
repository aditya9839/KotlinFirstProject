package com.example.kotlinfirstproject


//import com.example.kotlinfirstproject.AlarmReceiver
//import a
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
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context.LOCATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.location.LocationManager
import androidx.core.app.ComponentActivity.ExtraData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.provider.Settings


class MainActivity : AppCompatActivity() {

    //    private val mReceiver = MyReceiver()
    lateinit var geofencingClient: GeofencingClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun startService(view: View) {
        val input = edit_text.text

        Log.d("TAG", "this is first time I am printing something on kotlin$input")


        var serviceIntent = Intent(this, MyService::class.java)
        serviceIntent.putExtra("inputExtr", input.toString())
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    fun stopService(view: View) {
        serviceIntent = Intent(this, MyService::class.java)
        stopService(serviceIntent)
    }

    var serviceIntent: Intent? = null
    fun check_permission(view: View) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("TAG", "No permission granted")

            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                0
            )
            run {
                // permission has been granted, continue as usual
                val locationResult = LocationServices
                    .getFusedLocationProviderClient(
                        this
                        /** Context */
                    )
                    .getLastLocation()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("Tag", "onRequestPermissionsResult")

        val lm = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (!gps_enabled){
            var intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent)
        }
    }
}
//