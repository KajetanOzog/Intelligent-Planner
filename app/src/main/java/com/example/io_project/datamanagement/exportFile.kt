package com.example.io_project.datamanagement

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.tasks.await

suspend fun getUserFile(userId: String): String? {
    val db = Firebase.firestore
    val userDocRef = db.collection("users").document(userId)

    return try {
        val documentSnapshot = userDocRef.get().await()
        if (documentSnapshot.exists()) {
            val regularEventsByDay = mutableMapOf<String, Any?>()
            val daysOfWeek = listOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")

            for (day in daysOfWeek) {
                regularEventsByDay[day] = documentSnapshot.get("regular.$day")
            }

            // Retrieve non-regular events, tasks, completed and unfinished goals
            val nonregularEvents = documentSnapshot.get("nonregular.data")
            val tasks = documentSnapshot.get("tasks")
            val completedGoals = documentSnapshot.get("goals.completed")
            val unfinishedGoals = documentSnapshot.get("goals.unfinished")

            // Create a structured data map
            val dataStructure = mapOf(
                "Events" to mapOf(
                    "Regular" to regularEventsByDay,
                    "Nonregular" to nonregularEvents
                ),
                "Tasks" to tasks,
                "Goals" to mapOf(
                    "Completed" to completedGoals,
                    "Unfinished" to unfinishedGoals
                )
            )

            // Convert the data structure to JSON format
            Gson().toJson(dataStructure)
        } else {
            Log.d("getUserFile", "Document does not exist")
            null
        }
    } catch (e: Exception) {
        Log.d("getUserFile", "An error occurred while exporting data: ${e.message}")
        null
    }
}
