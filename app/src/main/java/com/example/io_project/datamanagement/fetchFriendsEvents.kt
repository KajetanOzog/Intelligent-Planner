package com.example.io_project.datamanagement

import android.util.Log
import com.example.io_project.Constants.FRIEND_COLOR_HEX
import com.example.io_project.dataclasses.Event

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
                    returnList.addAll(visibleEvents)
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
