package com.example.io_project.datamanagement
import com.example.io_project.datamanagement.getDayOfWeek
import android.util.Log
import com.example.io_project.dataclasses.Event
import com.google.firebase.firestore.DocumentSnapshot
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

suspend fun fetchAllEvents(userID: String): List<Event>? {
    val documentSnapshot = getUserDocument(userID)
    val returnList = ArrayList<Event>()
    try {

        if (documentSnapshot != null && documentSnapshot.exists()) {

            val daysOfWeek =  arrayOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")
            for(i in 0..6)
            {
                val regularData = documentSnapshot
                    .get("regular.${daysOfWeek[i]}") as List<Map<String, Event>>
                Log.d("FetchEvents","Fetched events list: ${regularData}")

                val allEvents = mutableListOf<Event>()

                if (regularData is List<*>) {
                    @Suppress("UNCHECKED_CAST")
                    for (event in regularData) {
                        returnList.add(mapToEvent(event))
                    }
                    Log.d("FetchEvents","ConvertedEvents: ${regularData}")

//                allEvents.addAll(regularDataForDayOfWeek as List<Event>)
                }
            }

//
//            val nonRegularData = documentSnapshot.get("nonregular") //as List<Map<String, Event>>
//            Log.d("FetchEvents(NonReg)","ConvertedEvents: ${nonRegularData}")
//            //nonRegularData.filter { event -> event["date"].toString() == targetDate }
//            //Log.d("FetchEvents(NonReg)","ConvertedEvents: ${nonRegularData}")


            val nonRegularData = getNonRegularData(documentSnapshot)
            if (nonRegularData != null) {
                for (event in nonRegularData) {
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

private fun getNonRegularData(documentSnapshot: DocumentSnapshot): List<Map<String, Event>>?
{
    val nonRegularData = documentSnapshot.get("nonregular.data") as List<Map<String, Event>>
    Log.d("FetchEvents(NonReg)","ConvertedEvents: ${nonRegularData}")

    if (nonRegularData is List<*>) {
        @Suppress("UNCHECKED_CAST")
        return nonRegularData
    }

    return null
}