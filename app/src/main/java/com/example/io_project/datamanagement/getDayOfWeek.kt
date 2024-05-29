package com.example.io_project.datamanagement

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun getDayOfWeek(date: String): String {
    val formatter = DateTimeFormatter.ofPattern("EEE, MMM d yyyy", Locale.ENGLISH)
    val localDate = LocalDate.parse(date, formatter)
    return when (localDate.dayOfWeek) {
        DayOfWeek.MONDAY -> "monday"
        DayOfWeek.TUESDAY -> "tuesday"
        DayOfWeek.WEDNESDAY -> "wednesday"
        DayOfWeek.THURSDAY -> "thursday"
        DayOfWeek.FRIDAY -> "friday"
        DayOfWeek.SATURDAY -> "saturday"
        DayOfWeek.SUNDAY -> "sunday"
    }
}