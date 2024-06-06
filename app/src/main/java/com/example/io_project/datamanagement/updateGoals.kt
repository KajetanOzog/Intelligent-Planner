package com.example.io_project.datamanagement
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
        userDocumentRef.update("goals.unfinished", uncompletedGoals).await()
        println("Unfinished goals updated.")

        userDocumentRef.update("goals.completed", FieldValue.arrayUnion(*completedGoals.toTypedArray())).await()
        println("Completed goals updated.")
    } catch (e: Exception) {
        println("Error when updating goals: ${e.message}")
        e.printStackTrace()
    }
}