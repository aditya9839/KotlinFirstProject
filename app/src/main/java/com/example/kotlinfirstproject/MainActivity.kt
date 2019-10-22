package com.example.kotlinfirstproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.GeofencingClient
import kotlinx.android.synthetic.main.activity_main.*

//import AlarmReceiver
//import android.app.AlarmManager

class MainActivity : AppCompatActivity() {

//    private val mReceiver = MyReceiver()
    lateinit var geofencingClient: GeofencingClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



//        val filter = IntentFilter()
//        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
//        filter.addAction(Intent.ACTION_POWER_CONNECTED)
//        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
//        filter.addAction(Intent.ACTION_BOOT_COMPLETED)
//        filter.addAction(Intent.ACTION_BOOT_COMPLETED)
//        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));
//        Register the receiver using the activity context
//        this.registerReceiver(mReceiver, filter)

//        var alarm = Intent(this,AlarmReceiver::class.java)
//        startService(alarm)
//        var alarmRunning = (PendingIntent.getBroadcast(this, 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);
//
//        Log.d("This is tag",""+alarmRunning)
//        if (alarmRunning == false) {
//            Log.d("TAGGG","running....")
//            val pendingIntent = PendingIntent.getBroadcast(this, 0, alarm, 0)
//            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            alarmManager.setRepeating(
//                AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                SystemClock.elapsedRealtime(),
//                1000000,
//                pendingIntent
//            )
//        }
//        Log.d("This is tag",""+alarmRunning)
//
    }

    fun startService(view: View) {
        val input = edit_text.text

        Log.d("TAG", "this is first time I am printing something on kotlin$input")


        var serviceIntent = Intent(this,MyService::class.java)
        serviceIntent.putExtra("inputExtr",input.toString())
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    fun stopService(view: View) {
        serviceIntent = Intent(this,MyService::class.java)
        stopService(serviceIntent)
    }

    var serviceIntent : Intent? = null
}
