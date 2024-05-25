package com.example.io_project.datamanagement

import com.example.io_project.dataclasses.Event
import com.google.firebase.firestore.DocumentSnapshot
import getDayOfWeek
import java.text.SimpleDateFormat
import java.util.*

suspend fun fetchEvents(userID: String, targetDate: String): List<Event>? {
    val documentSnapshot = getUserDocument(userID)

    try {

        if (documentSnapshot != null && documentSnapshot.exists()) {
            val dayOfWeek = getDayOfWeek(targetDate)

            val regularDataForDayOfWeek = documentSnapshot.get("regular.$dayOfWeek")

            val nonRegularDataForDate = getNonRegularDataForDate(documentSnapshot, targetDate)

            val allEvents = mutableListOf<Event>()

            if (regularDataForDayOfWeek is List<*>) {
                @Suppress("UNCHECKED_CAST")
                allEvents.addAll(regularDataForDayOfWeek as List<Event>)
            }

            if (nonRegularDataForDate != null) {
                allEvents.addAll(nonRegularDataForDate)
            }

            return allEvents
        } else {
            println("Dokument użytkownika nie istnieje lub nie został pobrany")
        }
    } catch (e: Exception) {
        println("Błąd podczas pobierania danych dla danego dnia: ${e.message}")
        e.printStackTrace()
    }

    return null
}

private fun getNonRegularDataForDate(documentSnapshot: DocumentSnapshot, targetDate: String): List<Event>? {
    val nonRegularData = documentSnapshot.get("nonregular")

    if (nonRegularData is List<*>) {
        @Suppress("UNCHECKED_CAST")
        val filteredNonRegularData = nonRegularData.filterIsInstance<Event>().filter { it.date == targetDate }
        return filteredNonRegularData
    }

    return null
}

fun getDayOfWeek(dateString: String): String {
    val dateFormat = SimpleDateFormat("EEE, MMM dd yyyy", Locale.ENGLISH)
    val date = dateFormat.parse(dateString)
    val calendar = Calendar.getInstance()
    calendar.time = date
    val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH)
    return dayOfWeek.toLowerCase()
}