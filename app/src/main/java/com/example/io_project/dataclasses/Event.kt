package com.example.io_project.dataclasses

data class Event(
    var eventID: String = "",
    override val name: String = "",
    val category: String = "",
    var color: String = "",
    val date: String = "",
    val place: String = "",
    val time: String = "",
    val endDate: String = "",
    val endTime: String = "",
    val weekly: Boolean = false,
    val reminder: Boolean = false,
    val alarm: Boolean = false,
    val reminderTime: String = "",
    val visible: Boolean = false,
    val description: String = "",
    val priority: EventPriority
) : Activity(name)