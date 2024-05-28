package com.example.io_project.dataclasses

data class Group(
    val ownerID: String,
    val groupName: String,
    var groupID: String,
    val groupMembers: List<String>,
    val events: List<Event>
)