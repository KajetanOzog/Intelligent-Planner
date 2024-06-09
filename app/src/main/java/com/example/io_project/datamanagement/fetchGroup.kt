package com.example.io_project.datamanagement
import android.util.Log
import com.example.io_project.Constants.GROUP_COLOR_HEX
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.EventPriority
import com.example.io_project.dataclasses.Group

// Function to fetch group data
suspend fun fetchGroup(groupID: String): Group? {
    try {
        @Suppress("UNCHECKED_CAST")
        val groupDocument = getGroupDocument()
        if(groupDocument != null) {
            val groupData = groupDocument.data?.get(groupID) as? Map<String, Any>
            return if (groupData != null) {
                mapToGroup(groupData)
            } else {
                Log.d("FetchGroup", "GroupDocument is null")
                null
            }
        }
    } catch (e: Exception) {
        Log.d("FetchGroup", "Error when fetching group: ${e.message}")
    }
    return null
}

// Function to map group data to Group object
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

// Function to map event data to Event object
private fun mapToEvent(event: Map<String, Event>): Event {
    return Event(
        eventID = event["eventID"].toString(),
        name = event["name"].toString(),
        category = event["category"].toString(),
        color = GROUP_COLOR_HEX,
        date = event["date"].toString(),
        place = event["place"].toString(),
        time = event["time"].toString(),
        endDate = event["endDate"].toString(),
        endTime = event["endTime"].toString(),
        weekly = event["weekly"].toString() == "true",
        reminder = event["reminder"].toString() == "true",
        alarm = event["alarm"].toString() == "true",
        reminderTime = event["reminderDate"].toString(),
        visible = event["visible"].toString() == "true",
        description = event["description"].toString(),
        priority = EventPriority.valueOf(event["priority"].toString())
    )
}
