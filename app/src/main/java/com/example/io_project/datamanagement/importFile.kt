package com.example.io_project.datamanagement
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.EventPriority
import com.example.io_project.dataclasses.Goal
import com.example.io_project.dataclasses.Task
import com.google.common.reflect.TypeToken
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.tasks.await
import kotlin.math.floor


suspend fun importUserFile(userId: String, jsonString: String) {
    val db = Firebase.firestore
    val userDocRef = db.collection("users").document(userId)

    try {
        val gson = Gson()
        val mapType = object : TypeToken<Map<String, Any>>() {}.type
        val dataMap: Map<String, Any> = gson.fromJson(jsonString, mapType)
        val regularEvents = (dataMap["Events"] as Map<String, Any>)["Regular"] as Map<String, List<Map<String, Any>>>
        val nonregularEvents = (dataMap["Events"] as Map<String, Any>)["Nonregular"] as List<Map<String, Any>>
        val tasks = dataMap["Tasks"] as List<Map<String, Any>>
        val completedGoals = (dataMap["Goals"] as Map<String, Any>)["Completed"] as List<Map<String, Any>>
        val unfinishedGoals = (dataMap["Goals"] as Map<String, Any>)["Unfinished"] as List<Map<String, Any>>

        val regularEventObjectsByDay = regularEvents.mapValues { (_, events) ->
            events.map { event ->
                Event(
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
                    reminderTime = event["reminderTime"].toString(),
                    visible = event["visible"].toString() == "true",
                    description = event["description"].toString(),
                    priority = EventPriority.valueOf(event["priority"].toString())
                )
            }
        }
        val nonregularEventObjects = nonregularEvents.map { event ->
            Event(
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
                reminderTime = event["reminderTime"].toString(),
                visible = event["visible"].toString() == "true",
                description = event["description"].toString(),
                priority = EventPriority.valueOf(event["priority"].toString())
            )
        }
        val completedGoalObjects = completedGoals.map { goal ->
            Goal(
                name = goal["name"].toString(),
                deadline = goal["deadline"].toString(),
                done = goal["done"].toString() == "true"
            )
        }
        val taskObjects = tasks.map { task ->
            val doneCount = floor(task["doneCount"].toString().toFloatOrNull() ?: 0.0f).toInt()
            val max = floor(task["maxStreak"].toString().toFloatOrNull() ?: 0.0f).toInt()
            Task(
                name = task["name"].toString(),
                completed = task["completed"].toString() == "true",
                doneCount = doneCount,
                addedDate = task["addedDate"].toString(),
                maxStreak = max,
                lastCheck = task["lastCheck"].toString()
            )
        }
        val unfinishedGoalObjects = unfinishedGoals.map { goal ->
            Goal(
                name = goal["name"].toString(),
                deadline = goal["deadline"].toString(),
                done = goal["done"].toString() == "true"
            )
        }
        regularEventObjectsByDay.forEach { (day, events) ->
            userDocRef.update("regular.$day", events).await()
        }

        userDocRef.update("nonregular.data", nonregularEventObjects).await()
        userDocRef.update("tasks", taskObjects).await()
        userDocRef.update("goals.completed", completedGoalObjects).await()
        userDocRef.update("goals.unfinished", unfinishedGoalObjects).await()

    } catch (e: Exception) {
        println("An error occurred while exporting data to Firestore: ${e.message}")
        e.printStackTrace()
    }
}