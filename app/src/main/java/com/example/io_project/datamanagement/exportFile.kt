package com.example.io_project.datamanagement
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

            val nonregularEvents = documentSnapshot.get("nonregular.data")
            val tasks = documentSnapshot.get("tasks")

            val completedGoals = documentSnapshot.get("goals.completed")
            val unfinishedGoals = documentSnapshot.get("goals.unfinished")

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
            Gson().toJson(dataStructure)
        } else {
            println("Document does not exist")
            null
        }
    } catch (e: Exception) {
        println("An error occurred while exporting data: ${e.message}")
        e.printStackTrace()
        null
    }
}
