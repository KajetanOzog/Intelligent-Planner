package com.example.io_project.dataclasses

data class Task(
    val name: String,
    val completed: Boolean,
    val daysCount: Int,
    val daysCounter: Int,
    val maxStreak: Int
)

