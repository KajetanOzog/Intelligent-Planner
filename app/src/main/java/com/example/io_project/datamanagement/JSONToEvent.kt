package com.example.io_project.datamanagement

import com.example.io_project.dataclasses.Event
import com.google.gson.Gson

fun JSONToEvent(eventJSON: String): Event {
    return Gson().fromJson(eventJSON, Event::class.java)
}