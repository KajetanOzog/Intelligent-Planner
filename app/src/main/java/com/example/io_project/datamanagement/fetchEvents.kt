package com.example.io_project.datamanagement

import android.util.Log
import com.example.io_project.dataclasses.Event
import com.google.firebase.firestore.DocumentSnapshot
import getDayOfWeek
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

suspend fun fetchEvents(userID: String, targetDate: String): List<Event>? {
    val documentSnapshot = getUserDocument(userID)
    val returnList = ArrayList<Event>()
    try {

        if (documentSnapshot != null && documentSnapshot.exists()) {
            val dayOfWeek = getDayOfWeek(targetDate)

            val regularDataForDayOfWeek = documentSnapshot
                .get("regular.$dayOfWeek") as List<Map<String, Event>>
            Log.d("FetchEvents","Fetched events list: ${regularDataForDayOfWeek}")

            val allEvents = mutableListOf<Event>()

            if (regularDataForDayOfWeek is List<*>) {
                @Suppress("UNCHECKED_CAST")
                for (event in regularDataForDayOfWeek) {
                    returnList.add(mapToEvent(event))
                }
                Log.d("FetchEvents","ConvertedEvents: ${regularDataForDayOfWeek}")

//                allEvents.addAll(regularDataForDayOfWeek as List<Event>)
            }
//
//            val nonRegularData = documentSnapshot.get("nonregular") //as List<Map<String, Event>>
//            Log.d("FetchEvents(NonReg)","ConvertedEvents: ${nonRegularData}")
//            //nonRegularData.filter { event -> event["date"].toString() == targetDate }
//            //Log.d("FetchEvents(NonReg)","ConvertedEvents: ${nonRegularData}")


            val nonRegularDataForDate = getNonRegularDataForDate(documentSnapshot, targetDate)
            if (nonRegularDataForDate != null) {
                for (event in nonRegularDataForDate) {
                    returnList.add(mapToEvent(event))
                }
//                allEvents.addAll(nonRegularDataForDate)
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

private fun mapToEvent(event: Map<String, Event>): Event {
    return Event(
        name = event["name"].toString(),
        category = event["category"].toString(),
        color = event["color"].toString(),
        date = event["date"].toString(),
        place = event["place"].toString(),
        time = event["time"].toString(),
        endDate = event["endDate"].toString(),
        weekly = event["weekly"].toString() == "true",
        reminder = event["reminder"].toString() == "true",
        alarm = event["alarm"].toString() == "true",
        reminderTime = event["reminderDate"].toString(),
        visible = event["visible"].toString() == "true",
        description = event["description"].toString()
    )
}



private fun getNonRegularDataForDate(
    documentSnapshot: DocumentSnapshot,
    targetDate: String
): List<Map<String, Event>>? {
    val nonRegularData = documentSnapshot.get("nonregular.data") as List<Map<String, Event>>
    Log.d("FetchEvents(NonReg)","ConvertedEvents: ${nonRegularData}")

    if (nonRegularData is List<*>) {
        @Suppress("UNCHECKED_CAST")
        val filteredNonRegularData =
            nonRegularData.filter { it["date"].toString() == targetDate }
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