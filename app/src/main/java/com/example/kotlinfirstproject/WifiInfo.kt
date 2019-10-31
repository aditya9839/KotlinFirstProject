package com.example.kotlinfirstproject

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.util.Log
import androidx.core.content.ContextCompat

class WifiInfo(private val mctx: Context) : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {

        Log.d("WifiReceiver", "Have Wifi Connection")
        getWifiInfo()
    }

    private var wifiManager: WifiManager ?= null
    private fun getWifiInfo(){

        if (ContextCompat.checkSelfPermission(mctx, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            Log.d("TAG","No permission granted")
        }

        wifiManager = mctx.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        if(wifiManager!!.isWifiEnabled) {
            Log.d("Tag", "wifi is enable")
            val info = wifiManager!!.connectionInfo
            Log.d("TAG", "bssid is "+info.bssid)
        }
        else
        Log.d("Tag", "Kindly enable wifi")
    }
}