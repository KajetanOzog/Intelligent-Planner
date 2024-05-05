package com.example.io_project.dataclasses

data class Event(
    val name: String,
    val category: String,
    val color: String,
    val date: String,
    val place: String,
    val time: String,
    val endDate: String,
    val weekly: Boolean,
    val reminder: Boolean,
    val alarm: Boolean,
    val reminderTime: String,
    val visible: Boolean,
    val description: String
)
