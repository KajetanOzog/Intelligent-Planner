package com.example.io_project.datamanagement

import android.util.Log
import com.example.io_project.dataclasses.Goal
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun addGoalToFirestore(userID: String, goal: Goal, isCompleted: Boolean) {
    val firestore = FirebaseFirestore.getInstance()
    val userDocumentRef = firestore.collection("users").document(userID)

    try {
        if (isCompleted) {
            // Adding a completed goal
            Log.d("AddGoalToFirestore", "Completed goal added")
            userDocumentRef.update("goals.completed", FieldValue.arrayUnion(goal)).await()
        } else {
            // Adding an unfinished goal
            Log.d("AddGoalToFirestore", "Unfinished goal added")
            userDocumentRef.update("goals.unfinished", FieldValue.arrayUnion(goal)).await()
        }
        Log.d("AddGoalToFirestore", "Goal added")
    } catch (e: Exception) {
        Log.d("AddGoalToFirestore", "Error when adding goal: ${e.message}")
    }
}
