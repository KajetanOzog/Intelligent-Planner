package com.example.io_project.dataclasses

import android.net.Uri
import com.example.io_project.Constants.DEFAULT_COLOR_HEX
import com.google.gson.Gson

data class Event(
    val name: String = "",
    val category: String = "",
    val color: String = "",
    val date: String = "",
    val place: String = "",
    val time: String = "",
    val endDate: String = "",
    val weekly: Boolean = false,
    val reminder: Boolean = false,
    val alarm: Boolean = false,
    val reminderTime: String = "",
    val visible: Boolean = false,
    val description: String = ""
) {
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}
