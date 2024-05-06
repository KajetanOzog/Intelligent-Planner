package com.example.io_project.datamanagement

import com.example.io_project.dataclasses.Goal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

suspend fun addGoalToFirestore(userID: String, goal: Goal, isCompleted: Boolean) {
    val firestore = FirebaseFirestore.getInstance()
    val userDocumentRef = firestore.collection("users").document(userID)

    val goalData = hashMapOf<String, Any>(
        "name" to goal.name,
        "deadline" to goal.deadline,
        "done" to goal.done
    )
    val goalsRef = if (isCompleted) {
        userDocumentRef.collection("goals").document("completed")
    }
    else
    {
        userDocumentRef.collection("goals").document("unfinished")
    }
    goalsRef.update("goals", FieldValue.arrayUnion(goalData))
}