package com.example.io_project.datamanagement

import com.example.io_project.dataclasses.Task

suspend fun getTasks(userID: String): List<Task>? {
    val documentSnapshot = getUserDocument(userID)

    try {

        if (documentSnapshot != null && documentSnapshot.exists()) {
            val tasksData = documentSnapshot.get("tasks")

            if (tasksData is List<*>)
            {
                @Suppress("UNCHECKED_CAST")
                return tasksData as List<Task>
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