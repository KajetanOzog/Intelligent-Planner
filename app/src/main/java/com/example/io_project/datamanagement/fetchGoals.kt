package com.example.io_project.datamanagement

import android.util.Log
import com.example.io_project.dataclasses.Goal

// Function to fetch uncompleted goals for a user
suspend fun fetchUncompletedGoals(userID: String): List<Goal>? {
    val returnList = ArrayList<Goal>()
    val documentSnapshot = getUserDocument(userID)
    try {
        if (documentSnapshot != null && documentSnapshot.exists()) {
            // Retrieve uncompleted goals data from the user document
            val uncompletedGoalsData =  documentSnapshot
                .get("goals.unfinished") as List<Map<String, Any>>
            for (goal in uncompletedGoalsData) {
                if (goal != null) {
                    Log.d(
                        "FetchUncompletedGoals",
                        "Single fetched goal: ${goal.javaClass.simpleName} -> ${goal}"
                    )
                    returnList.add(
                        Goal(
                            name = goal["name"].toString(),
                            deadline = goal["deadline"].toString(),
                            done = goal["done"].toString() == "true"
                        )
                    )
                }
            }
            @Suppress("UNCHECKED_CAST")
            return returnList
        } else {
            Log.d("FetchUncompletedGoals", "User document does not exist or was not fetched")
        }
    } catch (e: Exception) {
        Log.d("FetchUncompletedGoals", "Error while fetching uncompleted goals: ${e.message}")
    }
    return null
}

// Function to fetch completed goals for a user
suspend fun fetchCompletedGoals(userID: String): List<Goal>? {
    val returnList = ArrayList<Goal>()
    val documentSnapshot = getUserDocument(userID)
    try {
        if (documentSnapshot != null && documentSnapshot.exists()) {
            // Retrieve completed goals data from the user document
            val completedGoalsData =  documentSnapshot
                .get("goals.completed") as List<Map<String, Any>>
            for (goal in completedGoalsData) {
                if (goal != null) {
                    Log.d(
                        "FetchCompletedGoals",
                        "Single fetched goal: ${goal.javaClass.simpleName} -> ${goal}"
                    )
                    returnList.add(
                        Goal(
                            name = goal["name"].toString(),
                            deadline = goal["deadline"].toString(),
                            done = goal["done"].toString() == "true"
                        )
                    )
                }
            }
            @Suppress("UNCHECKED_CAST")
            return returnList
        } else {
            Log.d("FetchCompletedGoals", "User document does not exist or was not fetched")
        }
    } catch (e: Exception) {
        Log.d("FetchCompletedGoals", "Error while fetching completed goals: ${e.message}")
    }
    return null
}

