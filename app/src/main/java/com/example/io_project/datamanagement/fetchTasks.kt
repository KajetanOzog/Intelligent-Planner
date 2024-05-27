package com.example.io_project.datamanagement

import com.example.io_project.dataclasses.Task

suspend fun getTasks(userID: String): List<Task>? {
    val documentSnapshot = getUserDocument(userID)
    val returnList = ArrayList<Task>()
    try {

        if (documentSnapshot != null && documentSnapshot.exists()) {
            @Suppress("UNCHECKED_CAST")
            val tasksData = documentSnapshot.get("tasks") as List<Map<String, Task>>

            if (tasksData is List<*>)
            {
                for (task in tasksData) {
                    returnList.add (
                        Task(
                            name = task["name"].toString(),
                            completed = task["completed"].toString() == "true",
                            daysCount = task["daysCount"].toString().toInt(),
                            daysCounter = task["daysCounter"].toString().toInt(),
                            maxStreak = task["maxStreak"].toString().toInt()
                        )
                    )
                }

                return returnList
            }
        } else {
            println("Dokument użytkownika nie istnieje lub nie został pobrany")
        }
    } catch (e: Exception) {
        println("Błąd podczas pobierania zadań: ${e.message}")
        e.printStackTrace()
    }

    return null
}