package com.example.kotlinfirstproject

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.IBinder
import androidx.core.app.NotificationCompat
import android.util.Log


class MyService : Service() {

    private val mReceiver = WifiInfo(this)


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val inputt = intent?.getStringExtra("inputExtr")
        val notificationIntent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0)
        val notification = NotificationCompat.Builder(this,App().CHANNEL_ID).setContentTitle("Example Service").
            setContentText(inputt).setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent).build()
        startForeground(1,notification)


        //merging broadcast receiver with the service
        Log.d("tag","onStartCommand")

        val filter = IntentFilter()
        filter.addAction(WifiManager.RSSI_CHANGED_ACTION)
        this.registerReceiver(mReceiver, filter)
        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("not implemented")
    }

    override fun onDestroy() {
        Log.d("TAG","DESTROY")
        val alarm = getSystemService(ALARM_SERVICE) as AlarmManager
        alarm.set(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + (1000 *20), // One hour from now
            PendingIntent.getService(this, 0, Intent(this, MyService::class.java), 0)
        )
        unregisterReceiver(mReceiver)
    }
}