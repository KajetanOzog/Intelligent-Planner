package com.example.io_project.datamanagement

import android.util.Log
import com.example.io_project.dataclasses.Event
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

suspend fun fetchGroupsEvents(userID: String): Map<String, List<Event>>? {
    val groups = fetchUserGroups(userID)
    val groupEventsMap = mutableMapOf<String, List<Event>>()

    try {
        if (groups != null) {
            for (group in groups) {
                val eventsWithHour = ArrayList<Event>()
                val eventsWithoutHour = ArrayList<Event>()

                for (event in group.events) {
                    if (event.time.isEmpty()) {
                        eventsWithoutHour.add(event)
                    } else {
                        eventsWithHour.add(event)
                    }
                }

                // Sort events with time by time
                val timeFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)
                eventsWithHour.sortWith(compareBy { event ->
                    try {
                        timeFormat.parse(event.time)
                    } catch (e: ParseException) {
                        Log.d("FetchGroupsEvents", "Error parsing time for event: ${event.time}")
                        null
                    }
                })

                // Combine lists: events without time at the beginning
                val sortedEvents = ArrayList<Event>().apply {
                    addAll(eventsWithoutHour)
                    addAll(eventsWithHour)
                }

                groupEventsMap[group.groupID] = sortedEvents
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
