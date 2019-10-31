package com.example.kotlinfirstproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver : BroadcastReceiver()
{
    override fun onReceive(p0: Context?, p1: Intent?) {

        Log.d("Onreceive","Ser")
        val schedule = Utilll()
        if (p0 != null) {
            schedule.scheduleJob()
        }
//        val background = Intent(p0, MyService::class.java)
//        p0?.startService(background)
     }
}