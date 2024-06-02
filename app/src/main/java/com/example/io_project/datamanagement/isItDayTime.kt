package com.example.io_project.datamanagement

import android.util.Log
import com.example.io_project.Constants.DAY_BEGINNING_TIME
import com.example.io_project.Constants.DAY_ENDING_TIME
import java.time.LocalTime

fun isItDayTime(): Boolean {
    return try {
        val currentHour = LocalTime.now().hour
        (currentHour in (DAY_BEGINNING_TIME + 1)..<DAY_ENDING_TIME)
    } catch (_: Exception) {
        Log.d("IsItDayTime", "Problems occurred while getting current hour")
        true
    }
}