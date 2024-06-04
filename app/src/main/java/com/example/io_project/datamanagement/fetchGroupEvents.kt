package com.example.io_project.datamanagement
import com.example.io_project.dataclasses.Event

suspend fun fetchGroupsEvents(userID: String): Map<String, List<Event>>? {
    val groups = fetchUserGroups(userID)
    val groupEventsMap = mutableMapOf<String, List<Event>>()
    try {
        if (groups != null) {
            for (group in groups) {
                groupEventsMap[group.groupID] = group.events
            }
            return groupEventsMap
        } else {
            println("No groups found for the user.")
        }
    } catch (e: Exception) {
        println("Error while fetching group events: ${e.message}")
        e.printStackTrace()
    }
    return null
}