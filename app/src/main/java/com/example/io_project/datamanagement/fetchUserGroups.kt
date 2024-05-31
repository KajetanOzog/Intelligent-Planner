package com.example.io_project.datamanagement

import android.util.Log
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.Group

suspend fun fetchGroups(userID: String): List<Group>? {
    val documentSnapshot = getUserDocument(userID)
    val returnList = ArrayList<Group>()
    try {
        if (documentSnapshot != null && documentSnapshot.exists()) {
            @Suppress("UNCHECKED_CAST")
            val groupIDs = documentSnapshot.get("groups") as? List<String> ?: emptyList()
            Log.d("FetchUserGroups", "GroupIDs: $groupIDs")
            for (groupID in groupIDs) {
                val groupDocument = getGroupDocument(groupID)
                if (groupDocument != null) {
                    Log.d("FetchUserGroup", "$groupDocument, ${groupDocument.exists()}")
                }
                if (groupDocument != null && groupDocument.exists()) {
                    val groupData = groupDocument.data as Map<String, Any>
                    Log.d("FetchUserGroups", "GroupData: $groupData")
                    val group = mapToGroup(groupData)
                    returnList.add(group)
                }
            }
            return returnList
        } else {
            println("Dokument użytkownika nie istnieje lub nie został pobrany")
        }
    } catch (e: Exception) {
        println("Błąd podczas pobierania danych dla danego dnia: ${e.message}")
        e.printStackTrace()
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
