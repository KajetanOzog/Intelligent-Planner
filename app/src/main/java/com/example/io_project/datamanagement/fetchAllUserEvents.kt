package com.example.io_project.datamanagement

/*suspend fun fetchAllUserEvents(userID: String, date: String): List<Event>? {
    val allEvents = ArrayList<Event>()

    // Fetch events from friends
    val friendsEvents: List<Event>? = fetchFriendsEvents(userID, date)
    friendsEvents?.let { allEvents.addAll(it) }

    // Fetch user's own events
    val userEvents: List<Event>? = fetchEvents(userID, date)
    userEvents?.let { allEvents.addAll(it) }

    // Fetch events from groups
    val groupsEvents: Map<String, List<Event>>? = fetchGroupsEvents(userID)
    groupsEvents?.forEach { (_, events) ->
        events.filter { it.date == date }.let { allEvents.addAll(it) }
    }

    // Sort events by time
    val timeFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    try {
        allEvents.sortWith(compareBy { event ->
            timeFormat.parse(event.time)
        })
    } catch (e: ParseException) {
        Log.d("FetchAllUserEvents", "Error parsing time: ${e.message}")
    }

    return if (allEvents.isEmpty()) null else allEvents
}*/


import android.util.Log
import com.example.io_project.dataclasses.Event
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
suspend fun fetchAllUserEvents(userID: String, date: String): List<Event>? {
    val allEvents = ArrayList<Event>()
    val eventsWithHour = ArrayList<Event>()
    val eventsWithoutHour = ArrayList<Event>()

    // Fetch events from friends
    val friendsEvents: List<Event>? = fetchFriendsEvents(userID, date)
    friendsEvents?.let { allEvents.addAll(it) }

    // Fetch user's own events
    val userEvents: List<Event>? = fetchEvents(userID, date)
    userEvents?.let { allEvents.addAll(it) }

    // Fetch events from groups
    val groupsEvents: Map<String, List<Event>>? = fetchGroupsEvents(userID)
    groupsEvents?.forEach { (_, events) ->
        events.filter { it.date == date }.let { allEvents.addAll(it) }
    }

    // Divide events into those with and without specified times
    for (event in allEvents) {
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
            Log.d("FetchAllUserEvents", "Error parsing time for event: ${event.time}")
            null
        }
    })

    // Combine lists: events without time at the beginning
    val sortedEvents = ArrayList<Event>().apply {
        addAll(eventsWithoutHour)
        addAll(eventsWithHour)
    }

    return if (sortedEvents.isEmpty()) null else sortedEvents
}

