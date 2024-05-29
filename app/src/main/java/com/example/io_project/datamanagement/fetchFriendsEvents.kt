package com.example.io_project.datamanagement
import android.util.Log
import com.example.io_project.dataclasses.Event
import com.google.firebase.firestore.DocumentSnapshot
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

suspend fun fetchFriendsEvents(userID: String, targetDate: String): List<Event>? {
    val documentSnapshot = getUserDocument(userID)
    val returnList = ArrayList<Event>()

    try {
        if (documentSnapshot != null && documentSnapshot.exists()) {
            val friends = documentSnapshot.get("friends") as List<String>
            for (friendID in friends) {
                val friendEvents = fetchEvents(friendID, targetDate)
                if (friendEvents != null) {
                    returnList.addAll(friendEvents)
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