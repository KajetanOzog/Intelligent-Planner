package com.example.io_project.dataclasses

data class Goal(
    override val name: String = "",
    val deadline: String = "",
    var done: Boolean = false
) : Activity(name)
