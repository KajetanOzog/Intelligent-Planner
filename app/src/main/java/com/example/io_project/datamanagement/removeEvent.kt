package com.example.io_project.datamanagement

import android.util.Log
import com.example.io_project.dataclasses.Event
import com.google.firebase.firestore.FirebaseFirestore

fun removeEvent(event: Event, userID: String) {
    val firestore = FirebaseFirestore.getInstance()
    // Reference to the user's document in the "users" collection
    val userDocumentRef = firestore.collection("users").document(userID)

    userDocumentRef.get()
        .addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot != null && documentSnapshot.exists()) {
                // Handle weekly events
                if (event.weekly) {
                    val daysOfWeek = arrayOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")
                    for (day in daysOfWeek) {
                        val regularData = documentSnapshot.get("regular.$day") as? List<Map<String, Any>> ?: emptyList()
                        val updatedRegularData = regularData.filter { it["eventID"].toString() != event.eventID }
                        // Update the Firestore document
                        userDocumentRef.update("regular.$day", updatedRegularData)
                            .addOnSuccessListener {
                                Log.d("removeEvent", "Updated regular data for $day successfully")
                            }
                            .addOnFailureListener { e ->
                                Log.e("removeEvent", "Error updating regular data for $day: ${e.message}")
                            }
                    }
                } else {
                    // Handle non-weekly events
                    val nonRegularData = documentSnapshot.get("nonregular.data") as? List<Map<String, Any>> ?: emptyList()
                    val updatedNonRegularData = nonRegularData.filter { it["eventID"].toString() != event.eventID }
                    // Update the Firestore document
                    userDocumentRef.update("nonregular.data", updatedNonRegularData)
                        .addOnSuccessListener {
                            Log.d("removeEvent", "Updated non-regular data successfully")
                        }
                        .addOnFailureListener { e ->
                            Log.e("removeEvent", "Error updating non-regular data: ${e.message}")
                        }
                }
            } else {
                Log.d("removeEvent", "User document does not exist or was not fetched")
            }
        }
        .addOnFailureListener { e ->
            Log.e("removeEvent", "Error fetching user document: ${e.message}")
        }
}
