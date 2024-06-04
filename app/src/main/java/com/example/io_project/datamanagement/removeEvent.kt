package com.example.io_project.datamanagement
import com.example.io_project.dataclasses.Event
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun removeEvent(event: Event, userID: String) {
    val firestore = FirebaseFirestore.getInstance()
    val userDocumentRef = firestore.collection("users").document(userID)
    try {
        val documentSnapshot = userDocumentRef.get().await()
        if (documentSnapshot != null && documentSnapshot.exists())
        {
            if(event.weekly)
            {
                val daysOfWeek = arrayOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")
                for (day in daysOfWeek) {
                    val regularData = documentSnapshot.get("regular.$day") as List<Map<String, Event>>
                    val updatedRegularData = regularData.filter { it["eventID"].toString() != event.eventID }
                    userDocumentRef.update("regular.$day", updatedRegularData).await()
                }
            }
            else
            {
                val nonRegularData = documentSnapshot.get("nonregular.data") as List<Map<String, Event>>
                val updatedNonRegularData = nonRegularData.filter { it["eventID"].toString() != event.eventID }
                userDocumentRef.update("nonregular.data", updatedNonRegularData).await()
            }

        } else {
            println("User document does not exist or was not fetched")
        }
    } catch (e: Exception) {
        println("Error when removing event: ${e.message}")
        e.printStackTrace()
    }
}
