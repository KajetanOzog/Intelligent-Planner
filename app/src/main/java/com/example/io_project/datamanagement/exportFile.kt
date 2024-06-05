package com.example.io_project.datamanagement
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.Goal
import com.example.io_project.dataclasses.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.tasks.await

suspend fun exportUserFiler(userId: String): String {
    val db = Firebase.firestore
    val userDocRef = db.collection("users").document(userId)

    return try {
        val userSnapshot = userDocRef.get().await()
        val userData = userSnapshot.data ?: return "{}"

        val regularEventsByDay = mutableMapOf<String, List<Event>>()
        val daysOfWeek = listOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")
        for (day in daysOfWeek) {
            val eventsData = userData["regular.$day"] as? List<Map<String, Any>> ?: emptyList()
            val events = eventsData.map { data ->
                Gson().fromJson(Gson().toJson(data), Event::class.java)
            }
            regularEventsByDay[day] = events
        }

        val nonregularEventsData = userData["nonregular"] as? List<Map<String, Any>> ?: emptyList()
        val nonregularEvents = nonregularEventsData.map { data ->
            Gson().fromJson(Gson().toJson(data), Event::class.java)
        }

        val tasksData = userData["tasks"] as? List<Map<String, Any>> ?: emptyList()
        val tasks = tasksData.map { data ->
            Gson().fromJson(Gson().toJson(data), Task::class.java)
        }

        val completedGoalsData = userData["goals.completed"] as? List<Map<String, Any>> ?: emptyList()
        val completedGoals = completedGoalsData.map { data ->
            Gson().fromJson(Gson().toJson(data), Goal::class.java)
        }
        val uncompletedGoalsData = userData["goals.unfinished"] as? List<Map<String, Any>> ?: emptyList()
        val unfinishedGoals = uncompletedGoalsData.map { data ->
            Gson().fromJson(Gson().toJson(data), Goal::class.java)
        }

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
    } catch (e: Exception) {
        e.printStackTrace()
        "{}"
    }
}
