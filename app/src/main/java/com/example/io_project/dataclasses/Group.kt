package com.example.io_project.dataclasses

import android.net.Uri
import com.google.gson.Gson

data class Group(
    val ownerID: String = "",
    val groupName: String = "",
    var groupID: String = "",
    val groupMembers: List<String> = emptyList(),
    val events: List<Event> = emptyList()

) {
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}