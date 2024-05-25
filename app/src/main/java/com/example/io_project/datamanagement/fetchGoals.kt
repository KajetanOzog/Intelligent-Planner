package com.example.io_project.datamanagement

import com.example.io_project.dataclasses.Goal


suspend fun fetchUncompletedGoals(userID: String): List<Goal>? {
    val documentSnapshot = getUserDocument(userID)

    try {
        if (documentSnapshot != null && documentSnapshot.exists()) {
            val uncompletedGoalsData = documentSnapshot.get("goals.uncompleted")
            if (uncompletedGoalsData is List<*>)
            {
                @Suppress("UNCHECKED_CAST")
                return uncompletedGoalsData as List<Goal>
            }
        }
        else
        {
            println("Dokument użytkownika nie istnieje lub nie został pobrany")
        }
    } catch (e: Exception) {
        println("Błąd podczas pobierania nieukończonych celów: ${e.message}")
        e.printStackTrace()
    }
    return null
}