package com.example.io_project.dataclasses

object WeatherCurrent
{
    var temperature: Double? = null
    var code: Int? = null
    var acquired: Boolean = false
}

object WeatherForecast
{
    var temperatures: Array<Double?> = arrayOfNulls(7)
    var codes: Array<Int?> = arrayOfNulls(7)
    var acquired: Boolean = false
}

object GreetingData
{
    var date: String? = null
    var greetingText: String = "Dzień dobry"
    var displayName: String? = null
    var events: MutableList<Event>? = null
}