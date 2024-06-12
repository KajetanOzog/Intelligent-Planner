package com.example.io_project.dataclasses
import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("task_name") // Custom JSON field name for Task
    override val name: String = "",
    var completed: Boolean = false,
    var doneCount: Int = 0,
    var addedDate: String = "",
    var maxStreak: Int = 0,
    var lastCheck: String = ""
) : Activity(name)