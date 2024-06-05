package com.example.io_project.datamanagement
import com.example.io_project.dataclasses.Event
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

suspend fun fetchAllUserEvents(userID: String, date: String): List<Event>? {
    val allEvents = ArrayList<Event>()
    val friendsEvents: List<Event>? = fetchFriendsEvents(userID, date)
    friendsEvents?.let { allEvents.addAll(it) }

    val userEvents: List<Event>? = fetchEvents(userID, date)
    userEvents?.let { allEvents.addAll(it) }

    val groupsEvents: Map<String, List<Event>>? = fetchGroupsEvents(userID)
    groupsEvents?.forEach { (_, events) ->
        events.filter { it.date == date }.let { allEvents.addAll(it) }
    }

    val dateFormat = SimpleDateFormat("EEE, MMM d yyyy", Locale.ENGLISH)

    try {
        allEvents.sortWith(compareBy { event ->
            dateFormat.parse(event.date)
        })
    } catch (e: ParseException) {
        println("Error parsing date: ${e.message}")
    }

    return if (allEvents.isEmpty()) null else allEvents
}
