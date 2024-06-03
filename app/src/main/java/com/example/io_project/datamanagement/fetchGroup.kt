package com.example.io_project.datamanagement

import android.util.Log
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.Group

suspend fun fetchGroup(groupID: String): Group? {
    @Suppress("UNCHECKED_CAST")
    val groupDocument = getGroupDocument()
    if(groupDocument != null)
    {
        val groupData = groupDocument.data?.get(groupID) as? Map<String, Any>
        return if (groupData != null) {
            mapToGroup(groupData)
        } else {
            null
        }
    }
    return null
}

private fun mapToGroup(group: Map<String, Any>): Group {
    @Suppress("UNCHECKED_CAST")
    val events = (group["events"] as? List<Map<String, Event>>)?.map { mapToEvent(it) } ?: emptyList()

    @Suppress("UNCHECKED_CAST")
    val groupMembers = group["groupMembers"] as? List<String> ?: emptyList()

    return Group(
        groupName = group["groupName"].toString(),
        groupID = group["groupID"].toString(),
        ownerID = group["ownerID"].toString(),
        events = events,
        groupMembers = groupMembers
    )
}

private fun mapToEvent(event: Map<String, Event>): Event {
    return Event(
        name = event["name"].toString(),
        category = event["category"].toString(),
        color = event["color"].toString(),
        date = event["date"].toString(),
        place = event["place"].toString(),
        time = event["time"].toString(),
        endDate = event["endDate"].toString(),
        weekly = event["weekly"].toString() == "true",
        reminder = event["reminder"].toString() == "true",
        alarm = event["alarm"].toString() == "true",
        reminderTime = event["reminderDate"].toString(),
        visible = event["visible"].toString() == "true",
        description = event["description"].toString()
    )
}