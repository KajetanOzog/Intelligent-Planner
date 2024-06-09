package com.example.io_project.datamanagement

import android.app.Activity
import android.util.Log
import okhttp3.*
import java.io.IOException
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.example.io_project.R
import com.example.io_project.dataclasses.WeatherCurrent
import com.example.io_project.dataclasses.WeatherForecast
import com.example.io_project.permissions.AskingForPermissions
import com.example.io_project.permissions.checkLocationPermission
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

fun getIcon(weatherCode: Int) : Int
{
    return when(weatherCode)
    {
        0 -> R.drawable.ic_sunny
        1 -> R.drawable.ic_sunnycloudy
        2, 3 -> R.drawable.ic_cloudy
        45,48 -> R.drawable.ic_very_cloudy
        51,53,55,80,81,82 -> R.drawable.ic_rainshower
        56,57,66,67 -> R.drawable.ic_snowyrainy
        61,63,65 -> R.drawable.ic_rainy
        71,85,86 -> R.drawable.ic_snowy
        73,75,77 -> R.drawable.ic_heavysnow
        95 -> R.drawable.ic_thunder
        96,99 -> R.drawable.ic_rainythunder
        else -> R.drawable.ic_sunny
    }
}

fun loadWeatherInfo(activity: Activity)
{
    getLastLocation(activity,
        onSuccess = { lat, lon ->
            val url = "https://api.open-meteo.com/v1/forecast?latitude=$lat&longitude=$lon&current=temperature_2m,weather_code&daily=weather_code,apparent_temperature_max&timezone=auto"
            Log.d("URL", url)

            sendHttpRequest(url,
                onResponse =
                { response ->
                    parseJson(response,
                        onSuccess =
                        { temp, wc, arr1, arr2 ->
                            WeatherCurrent.temperature = temp
                            WeatherCurrent.code = wc
                            WeatherCurrent.acquired = true
                            for(i in 0..6)
                            {
                                WeatherForecast.temperatures[i] = arr1[i]
                                WeatherForecast.codes[i] = arr2[i]
                            }
                            WeatherForecast.acquired = true
                            Log.d("Weather", "acquired")
                        },
                        onError =
                        {
                            Log.d("Weather", "not acquired, data null or json parse fail")
                        })
                },
                onError =
                {
                    Log.d("Weather", "not acquired")
                }
            )
        }
    )
}

private fun sendHttpRequest(url: String, onResponse: (String) -> Unit, onError: () -> Unit) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .build()

    client.newCall(request).enqueue(object : Callback
    {
        override fun onFailure(call: Call, e: IOException)
        {
            onError()
        }

        override fun onResponse(call: Call, response: Response)
        {
            val responseBody = response.body?.string()
            if (response.isSuccessful && responseBody != null)
            {
                onResponse(responseBody)
            }
        }
    })
}

private fun parseJson(json: String, onSuccess: (Double, Int, Array<Double>, Array<Int>) -> Unit, onError: () -> Unit){
    try
    {
        val gson = Gson()
        val jsonObject = gson.fromJson(json, JsonObject::class.java)

        val current = jsonObject.getAsJsonObject("current")
        val temperature = current.get("temperature_2m").asDouble
        val weatherCode = current.get("weather_code").asInt

        val daily = jsonObject.getAsJsonObject("daily")
        val apparentTemperatureMax = daily.getAsJsonArray("apparent_temperature_max").map { it.asDouble }.toTypedArray()
        val weatherCodeArr = daily.getAsJsonArray("weather_code").map { it.asInt }.toTypedArray()

        onSuccess(temperature, weatherCode, apparentTemperatureMax, weatherCodeArr)
    }
    catch (e: Exception)
    {
        e.printStackTrace()
        onError()
    }
}

fun getCurrentLocation(activity: Activity)
{
    Log.d("Location", "update requested")
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

    if(checkLocationPermission(activity))
    {
        fusedLocationClient.requestLocationUpdates(
            LocationRequest.create(),
            object : LocationCallback()
            {
                override fun onLocationResult(locationResult: LocationResult)
                {
                    Log.d("Location", "update succeed")
                    fusedLocationClient.removeLocationUpdates(this)
                    AskingForPermissions.finished = true
                    loadWeatherInfo(activity)
                }
            },
            null
        )
    }
    else
    {
        Log.d("Location", "update fail, no permission")
        AskingForPermissions.finished = true
    }
}

private fun getLastLocation(activity: Activity, onSuccess: (Double, Double) -> Unit)
{
    Log.d("Location", "Getting last location")
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    if(checkLocationPermission(activity))
    {
        fusedLocationClient.lastLocation.addOnSuccessListener()
        {
                lct ->
            run {
                if (lct != null)
                {
                    Log.d("Location", "Last location acquired: ${lct.latitude}, ${lct.longitude}")
                    onSuccess(lct.latitude, lct.longitude)
                }
                else
                {
                    Log.d("Location", "Last location is null")
                }
            }
        }
    }
}