package com.example.io_project.datamanagement

import android.util.Log
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
            Log.d("FetchGroupsEvents", "No groups found for the user.")
        }
    } catch (e: Exception) {
        Log.d("FetchGroupsEvents", "Error while fetching group events: ${e.message}")
    }
    return null
}
