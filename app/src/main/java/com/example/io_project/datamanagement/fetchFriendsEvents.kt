package com.example.io_project.datamanagement

import android.util.Log
import com.example.io_project.Constants.FRIEND_COLOR_HEX
import com.example.io_project.dataclasses.Event
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

suspend fun fetchFriendsEvents(userID: String, targetDate: String): List<Event>? {
    val documentSnapshot = getUserDocument(userID)
    val returnList = ArrayList<Event>()
    try {
        if (documentSnapshot != null && documentSnapshot.exists()) {
            // Retrieve list of friends from the user document
            val friends = documentSnapshot.get("friends") as List<String>
            for (friendID in friends) {
                val friendEvents = fetchEvents(friendID, targetDate)
                if (friendEvents != null) {
                    // Filter visible events and assign friend's color
                    val visibleEvents = friendEvents.filter { it.visible }
                    visibleEvents.forEach { it.color = FRIEND_COLOR_HEX }
                    val currentDate = Date()
                    val dateFormat = SimpleDateFormat("EEE, MMM dd yyyy", Locale.ENGLISH)

                    // Filter the events based on endDate
                    val filteredEvents = visibleEvents.filter { event ->
                        val endDate = event.endDate.takeIf { it.isNotEmpty() }?.let { dateFormat.parse(it) }
                        endDate == null || endDate >= currentDate
                    }
                    returnList.addAll(filteredEvents)
                }
            }
            return returnList
        } else {
            Log.d("FetchFriendsEvents", "User document does not exist or was not fetched")
        }
    } catch (e: Exception) {
        Log.d("FetchFriendsEvents", "Error while fetching data for the given day: ${e.message}")
    }
    return null
}
