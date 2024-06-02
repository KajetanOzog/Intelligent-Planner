package com.example.io_project.dataclasses

object Weather
{
    var temperature: Double? = null
    var code: Int? = null
    var acquired: Boolean = false
}

object GreetingData
{
    var date: String? = null
    var greetingText: String = "Dzień dobry"
    var events: MutableList<Event>? = null
    var name: String? = null
}