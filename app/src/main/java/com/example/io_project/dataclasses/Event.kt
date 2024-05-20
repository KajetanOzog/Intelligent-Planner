package com.example.io_project.dataclasses

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
        return super.toString() + name + category + color + date + place + time + endDate + weekly + reminder + alarm + reminderTime + visible + description
    }
}
