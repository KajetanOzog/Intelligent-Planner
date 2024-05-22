package com.example.io_project.datamanagement

import com.example.io_project.dataclasses.Goal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun addGoalToFirestore(userID: String, goal: Goal, isCompleted: Boolean) {
    val firestore = FirebaseFirestore.getInstance()
    val userDocumentRef = firestore.collection("users").document(userID)

    val goalsRef = if (isCompleted) {
        println("Dodawanie celu skompletowanego")
        userDocumentRef.update("goals.completed", FieldValue.arrayUnion(goal)).await()
    }
    else
    {
        println("Dodawanie celu nieskompletowanego")
        userDocumentRef.update("goals.unfinished", FieldValue.arrayUnion(goal)).await()
    }
}