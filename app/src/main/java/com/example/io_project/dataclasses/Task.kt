package com.example.io_project.dataclasses

data class Task(
    override val name: String = "",
    var completed: Boolean = false,
    var doneCount: Int = 0,
    var addedDate: String = "",
    var maxStreak: Int = 0,
    var lastCheck: String = ""
) : Activity(name)

