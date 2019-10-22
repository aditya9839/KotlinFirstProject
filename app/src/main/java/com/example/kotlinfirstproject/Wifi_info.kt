package com.example.kotlinfirstproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.util.Log

class Wifi_info(val mctx: Context) : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {

        Log.d("WifiReceiver", "Have Wifi Connection");

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
        val connManager = mctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        Log.d("TAG","connManager " +connManager)

        val networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        Log.d("TAG","connManager " +networkInfo)

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