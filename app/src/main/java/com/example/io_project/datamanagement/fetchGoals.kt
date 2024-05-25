package com.example.io_project.datamanagement

import android.util.Log
import com.example.io_project.dataclasses.Goal


suspend fun fetchUncompletedGoals(userID: String): List<Goal>? {
    val documentSnapshot = getUserDocument(userID)
    Log.d("FetchGoals", "$documentSnapshot")
    try {
        if (documentSnapshot != null && documentSnapshot.exists()) {
            val uncompletedGoalsData = documentSnapshot.get("goals.uncompleted")
            Log.d("FetchGoals", "$uncompletedGoalsData")
            if (uncompletedGoalsData is List<*>)
            {
                @Suppress("UNCHECKED_CAST")
                return uncompletedGoalsData as List<Goal>
            }
            else {
                Log.d("FetchGoals", "UncompletedGoalsData is not a list")
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