package com.example.kotlinfirstproject

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat

class MyReceiver : BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d(TAG, "onReceiveeeeee: ");
        Toast.makeText(p0,"Broadcast receiver triggred",Toast.LENGTH_LONG).show()
        val serviceIntent = Intent(p0,MyService::class.java)
        if (p0 != null) {
//            ContextCompat.startForegroundService(p0, serviceIntent)
        }
    }
}

