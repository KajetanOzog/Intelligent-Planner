package com.example.io_project.datamanagement
import android.util.Log
import com.example.io_project.dataclasses.Task
import java.time.LocalDate
import java.time.format.DateTimeFormatter

suspend fun getTasks(userID: String): List<Task>? {
    val documentSnapshot = getUserDocument(userID)
    val returnList = ArrayList<Task>()
    try {
        if (documentSnapshot != null && documentSnapshot.exists()) {
            // Extract tasks data from document
            @Suppress("UNCHECKED_CAST")
            val tasksData = documentSnapshot.get("tasks") as List<Map<String, Task>>
            for (task in tasksData) {
                // Create Task object from data
                val taskObj = Task(
                    name = task["name"].toString(),
                    completed = task["completed"].toString() == "true",
                    doneCount = task["doneCount"].toString().toInt(),
                    addedDate = task["addedDate"].toString(),
                    maxStreak = task["maxStreak"].toString().toInt(),
                    lastCheck = task["lastCheck"].toString()
                )
                // Get today's date
                val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy MM dd"))
                Log.d("FetchTask", "$task, $today, ${taskObj.lastCheck}")
                // Check if task was completed today and reset completion status if necessary
                if (taskObj.lastCheck != today && taskObj.completed) {
                    taskObj.completed = false
                }
                returnList.add(taskObj)
            }
            return returnList
        } else {
            Log.d("FetchTask", "User document does not exist or was not fetched")
        }
    } catch (e: Exception) {
        Log.e("FetchTask", "Error while fetching tasks: ${e.message}")
    }
    // Return null if an error occurred
    return null
}
