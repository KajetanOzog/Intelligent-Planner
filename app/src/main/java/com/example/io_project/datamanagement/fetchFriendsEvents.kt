package com.example.io_project.datamanagement
import com.example.io_project.Constants.FRIEND_COLOR_HEX
import com.example.io_project.dataclasses.Event

suspend fun fetchFriendsEvents(userID: String, targetDate: String): List<Event>? {
    val documentSnapshot = getUserDocument(userID)
    val returnList = ArrayList<Event>()
    try {
        if (documentSnapshot != null && documentSnapshot.exists()) {
            val friends = documentSnapshot.get("friends") as List<String>
            for (friendID in friends) {
                val friendEvents = fetchEvents(friendID, targetDate)
                if (friendEvents != null) {
                    val visibleEvents = friendEvents.filter { it.visible }
                    visibleEvents.forEach { it.color = FRIEND_COLOR_HEX }
                    returnList.addAll(visibleEvents)
                }
            }
            return returnList
        }
        else
        {
            println("User document does not exist or was not fetched")
        }
    } catch (e: Exception) {
        println("Error while fetching data for the given day: ${e.message}")
        e.printStackTrace()
    }
    return null
}