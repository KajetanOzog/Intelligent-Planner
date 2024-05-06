package com.example.io_project.datamanagement

import com.example.io_project.dataclasses.Goal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

fun addGoalToFirestore(userId: String, goal: Goal, status: String) {
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    if (currentUser != null && currentUser.uid == userId) {
        val db = FirebaseFirestore.getInstance()
        val goalsCollection = db.collection("users").document(userId).collection("goals")

        goalsCollection.document(status).update("goals", FieldValue.arrayUnion(goal))
            .addOnSuccessListener {
                println("Goal added successfully")
            }
            .addOnFailureListener { e ->
                println("Error adding goal: $e")
            }
    }
    else
    {
        println("Wrong user ID")
    }
}
