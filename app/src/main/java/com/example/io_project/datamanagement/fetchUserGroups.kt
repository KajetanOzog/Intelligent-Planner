package com.example.io_project.datamanagement

import android.util.Log
import com.example.io_project.Constants
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.EventPriority
import com.example.io_project.dataclasses.Group

suspend fun fetchUserGroups(userID: String): List<Group>? {
    val documentSnapshot = getUserDocument(userID)
    val returnList = ArrayList<Group>()
    try {
        if (documentSnapshot != null && documentSnapshot.exists()) {
            @Suppress("UNCHECKED_CAST")
            val groupIDs = documentSnapshot.get("groups") as? List<String> ?: emptyList()
            Log.d("FetchUserGroups", "GroupIDs: $groupIDs")
            val groupDocument = getGroupDocument()
            // Iterate through group IDs
            for (groupID in groupIDs) {
                if (groupDocument != null) {
                    Log.d("FetchUserGroups", "$groupDocument, ${groupDocument.exists()}")
                }
                if (groupDocument != null && groupDocument.exists()) {
                    // Extract group data from group document
                    val groupData = groupDocument.data?.get(groupID) as? Map<String, Any>
                    Log.d("FetchUserGroups", "GroupData: $groupData")
                    if (groupData != null) {
                        val group = mapToGroup(groupData)
                        returnList.add(group)
                    }
                }
            }
            return returnList
        } else {
            Log.d("FetchUserGroups", "User document does not exist or was not fetched")
        }
    } catch (e: Exception) {
        Log.e("FetchUserGroups", "Error while fetching data for the given day: ${e.message}")
    }
    return null
}

// Map group data to Group object
private fun mapToGroup(group: Map<String, Any>): Group {
    // Extract events data from group and map it to Event objects
    @Suppress("UNCHECKED_CAST")
    val events = (group["events"] as? List<Map<String, Event>>)?.map { mapToEvent(it) } ?: emptyList()

    // Extract group members from group data
    @Suppress("UNCHECKED_CAST")
    val groupMembers = group["groupMembers"] as? List<String> ?: emptyList()

    // Create Group object from group data
    return Group(
        groupName = group["groupName"].toString(),
        groupID = group["groupID"].toString(),
        ownerID = group["ownerID"].toString(),
        events = events,
        groupMembers = groupMembers
    )
}

// Map event data to Event object
private fun mapToEvent(event: Map<String, Event>): Event {
    return Event(
        eventID = event["eventID"].toString(),
        name = event["name"].toString(),
        category = event["category"].toString(),
        color = Constants.GROUP_COLOR_HEX,
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
