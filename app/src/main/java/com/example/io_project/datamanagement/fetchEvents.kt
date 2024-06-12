package com.example.io_project.datamanagement
import android.util.Log
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.EventPriority
import com.google.firebase.firestore.DocumentSnapshot
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Function to fetch events for a user on a specific date
suspend fun fetchEvents(userID: String, targetDate: String): List<Event>? {
    val documentSnapshot = getUserDocument(userID)
    val returnList = ArrayList<Event>()
    val eventsWithoutHour = ArrayList<Event>()

    try {
        if (documentSnapshot != null && documentSnapshot.exists()) {
            val dayOfWeek = getDayOfWeek(targetDate)
            val regularDataForDayOfWeek = documentSnapshot.get("regular.$dayOfWeek") as? List<Map<String, Any>>
            Log.d("FetchEvents", "Fetched events list: $regularDataForDayOfWeek")

            if (regularDataForDayOfWeek != null) {
                for (event in regularDataForDayOfWeek) {
                    val eventObj = mapToEvent(event)
                    if (eventObj.time.isEmpty()) {
                        eventsWithoutHour.add(eventObj)
                    } else {
                        returnList.add(eventObj)
                    }
                }
                Log.d("FetchEvents", "Converted Events: $returnList")
            }

            val nonRegularDataForDate = getNonRegularDataForDate(documentSnapshot, targetDate)
            if (nonRegularDataForDate != null) {
                for (event in nonRegularDataForDate) {
                    val eventObj = mapToEvent(event)
                    if (eventObj.time.isEmpty()) {
                        eventsWithoutHour.add(eventObj)
                    } else {
                        returnList.add(eventObj)
                    }
                }
            }

            val currentDate = Date()
            val dateFormat = SimpleDateFormat("EEE, MMM dd yyyy", Locale.ENGLISH)

            // Filter the events in returnList based on endDate
            returnList.removeAll { event ->
                val endDate = event.endDate.takeIf { it.isNotEmpty() }?.let { dateFormat.parse(it) }
                endDate != null && endDate < currentDate
            }

            // Filter the events in eventsWithoutHour based on endDate
            eventsWithoutHour.removeAll { event ->
                val endDate = event.endDate.takeIf { it.isNotEmpty() }?.let { dateFormat.parse(it) }
                endDate != null && endDate < currentDate
            }

            val timeFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)
            returnList.sortWith(compareBy { event ->
                try {
                    timeFormat.parse(event.time)
                } catch (e: ParseException) {
                    Log.d("FetchEvents", "Error parsing time for event: ${event.time}")
                    null
                }
            })

            // Add events without time at the beginning
            returnList.addAll(0, eventsWithoutHour)

            Log.d("FetchEvents", "Sorted Events: $returnList")
            return returnList
        } else {
            Log.d("FetchEvents", "User document does not exist or was not fetched")
        }
    } catch (e: Exception) {
        Log.d("FetchEvents", "Error fetching user events for the day: ${e.message}")
    }
    return null
}



// Function to fetch all events for a user
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
                if (regularData is List<*>) {
                    @Suppress("UNCHECKED_CAST")
                    for (event in regularData) {
                        returnList.add(mapToEvent(event))
                    }
                    Log.d("FetchEvents","ConvertedEvents: ${regularData}")

                }
            }

            val nonRegularData = getNonRegularData(documentSnapshot)
            if (nonRegularData != null) {
                for (event in nonRegularData) {
                    returnList.add(mapToEvent(event))
                }
            }

            return returnList
        } else {
            Log.d("FetchAllEvents", "User document does not exist or was not fetched")
        }
    } catch (e: Exception) {
        Log.d("FetchAllEvents", "Error fetching user events: ${e.message}")
        e.printStackTrace()
    }

    return null
}


// Function to map event data from Firestore document to Event object
/*private fun mapToEvent(event: Map<String, Event>): Event {
    return Event(

        eventID = event["eventID"].toString(),
        name = event["name"].toString(),
        category = event["category"].toString(),
        color = event["color"].toString(),
        date = event["date"].toString(),
        place = event["place"].toString(),
        time = event["time"].toString(),
        endDate = event["endDate"].toString(),
        endTime = event["endTime"].toString(),
        weekly = event["weekly"].toString() == "true",
        reminder = event["reminder"].toString() == "true",
        alarm = event["alarm"].toString() == "true",
        reminderTime = event["reminderDate"].toString(),
        visible = event["visible"].toString() == "true",
        description = event["description"].toString(),
        priority = EventPriority.valueOf(event["priority"].toString())
    )
}*/
private fun mapToEvent(event: Map<String, Any>): Event {
    return Event(
        eventID = event["eventID"].toString(),
        name = event["name"].toString(),
        category = event["category"].toString(),
        color = event["color"].toString(),
        date = event["date"].toString(),
        place = event["place"].toString(),
        time = event["time"].toString(),
        endDate = event["endDate"].toString(),
        endTime = event["endTime"].toString(),
        weekly = event["weekly"].toString() == "true",
        reminder = event["reminder"].toString() == "true",
        alarm = event["alarm"].toString() == "true",
        reminderTime = event["reminderDate"].toString(),
        visible = event["visible"].toString() == "true",
        description = event["description"].toString(),
        priority = EventPriority.valueOf(event["priority"].toString().toUpperCase())
    )
}
// Function to retrieve non-regular events for a specific date
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