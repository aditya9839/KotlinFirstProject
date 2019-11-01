package com.example.kotlinfirstproject

import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices

class GeofenceEnter(var mCtx: MainActivity) {
    lateinit var geofencingClient: GeofencingClient
    lateinit var geoObj: Geofence

    fun geoClient() {
        geofencingClient = LocationServices.getGeofencingClient(mCtx)

        geoObj = Geofence.Builder().setRequestId("key").setCircularRegion(28.5930316,77.3418044,20F).
            setExpirationDuration(10000000).setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT).build()
    }

    private fun getGeofencingRequest(): GeofencingRequest {
        return GeofencingRequest.Builder().apply {
            setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            addGeofence(geoObj)
        }.build()
    }
}