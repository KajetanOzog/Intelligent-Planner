package com.example.io_project.dataclasses

data class Task(
    val name: String = "",
    val completed: Boolean = false,
    val daysCount: Int = 0,
    val daysCounter: Int = 0,
    val maxStreak: Int = 0
)

