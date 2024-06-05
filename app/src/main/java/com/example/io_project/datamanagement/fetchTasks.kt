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
            @Suppress("UNCHECKED_CAST")
            val tasksData = documentSnapshot.get("tasks") as List<Map<String, Task>>
            for (task in tasksData) {
                val taskObj = Task(
                    name = task["name"].toString(),
                    completed = task["completed"].toString() == "true",
                    doneCount = task["doneCount"].toString().toInt(),
                    addedDate = task["addedDate"].toString(),
                    maxStreak = task["maxStreak"].toString().toInt(),
                    lastCheck = task["lastCheck"].toString()
                )

                val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy MM dd"))
                Log.d("FetchTask", "$task, $today, ${taskObj.lastCheck}")

                if (taskObj.lastCheck != today && taskObj.completed)
                {
                    taskObj.completed = false
                }

                returnList.add(taskObj)
            }
            return returnList
        } else {
            println("User document does not exist or was not fetched")
        }
    } catch (e: Exception) {
        println("Error while fetching tasks: ${e.message}")
        e.printStackTrace()
    }
    return null
}