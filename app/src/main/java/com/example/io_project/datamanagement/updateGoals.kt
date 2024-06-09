package com.example.io_project.datamanagement

import android.util.Log
import com.example.io_project.dataclasses.Goal
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun updateGoals(goals: List<Goal>, userID: String) {
    val firestore = FirebaseFirestore.getInstance()
    val userDocumentRef = firestore.collection("users").document(userID)
    val completedGoals = goals.filter { it.done }
    val uncompletedGoals = goals.filter { !it.done }

    try {
        // Update Firestore with uncompleted goals
        userDocumentRef.update("goals.unfinished", uncompletedGoals).await()
        Log.d("updateGoals", "Unfinished goals updated.")

        // Update Firestore with completed goals
        userDocumentRef.update("goals.completed", FieldValue.arrayUnion(*completedGoals.toTypedArray())).await()
        Log.d("updateGoals", "Completed goals updated.")
    } catch (e: Exception) {
        Log.e("updateGoals", "Error when updating goals: ${e.message}")
    }
}
