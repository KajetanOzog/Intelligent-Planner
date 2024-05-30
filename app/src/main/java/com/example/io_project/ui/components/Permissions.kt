package com.example.io_project.ui.components

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.io_project.datamanagement.getCurrentLocation

private const val LOCATION_PERMISSION_REQUEST_CODE = 1001

object AskingForPermissions
{
    var started: Boolean = false
    var finished: Boolean = false
}

class Permissions(private val activity: Activity)
{
    init {
        AskingForPermissions.started = true
        permissionCheckInit()
    }

    private fun permissionCheckInit()
    {
        Log.d("Location", "initiated")
        checkAndRequestPermissions()
    }

    private fun checkAndRequestPermissions()
    {
        Log.d("Location", "checking and requesting")
        if (!checkLocationPermission(activity)) {
            askForLocationPerm()
        } else {
            getCurrentLocation(activity)
        }
    }

    private fun askForLocationPerm()
    {
        Log.d("Location", "asking for permission")
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }
}


fun checkLocationPermission(activity: Activity) : Boolean
{
    return (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
}