package com.example.io_project.dataclasses

data class Group(
    val groupName: String,
    val groupID: String,
    val groupMembers: List<String>,
    val events: List<Event>
)