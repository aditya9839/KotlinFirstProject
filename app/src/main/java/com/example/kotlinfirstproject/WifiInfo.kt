package com.example.kotlinfirstproject

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.util.Log
import androidx.core.content.ContextCompat

class WifiInfo(val mctx: Context) : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {

        Log.d("WifiReceiver", "Have Wifi Connection")

//        val conn = mctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val netInfo = conn.activeNetworkInfo
//        if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI)
//            Log.d("WifiReceiver", "Have Wifi Connection");
//        else
//            Log.d("WifiReceiver", "Don't have Wifi Connection");
//        if (p1.)
        getWifiInfo()
    }

    fun getWifiInfo(){

        if (ContextCompat.checkSelfPermission(mctx, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            Log.d("TAG","No permission granted")
        }

        val connManager = mctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager



        Log.d("TAG","connManager " +connManager)

        //get Network Info is depreciated
        val networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        Log.d("TAG", "connManager $networkInfo")

        //isConnected is depricated
        if (networkInfo.isConnected) {
            Log.d("tag","Connected")
            val wifiManager = mctx.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            if (wifiManager != null) {
                val wifiInfo = wifiManager.connectionInfo
                bssid = wifiInfo.bssid
                val ssid = wifiInfo.ssid
                val mac = wifiInfo.macAddress
                val linkSpeed = wifiInfo.linkSpeed
                val ip = wifiInfo.ipAddress
                Log.d(
                    "This is wifi Info", "\nBSSID : " + bssid +
                            "\nSSID : " + ssid + "\nMAC : " + mac + "\nLink speed : " + linkSpeed +
                            "\nIP : " + ip
                )
            }
        }
        else
            Log.d("tag","Not Connected")
    }

    companion object {
        var bssid = ""
    }
}