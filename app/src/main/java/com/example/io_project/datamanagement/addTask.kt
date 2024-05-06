package com.example.io_project.datamanagement

import com.example.io_project.dataclasses.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth

fun addTask(userId: String, task: Task) {
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    if (currentUser != null && currentUser.uid == userId)
    {
        val db = FirebaseFirestore.getInstance()
        val userTasksCollection = db.collection("users").document(userId).collection("tasks")

        userTasksCollection.add(task)
            .addOnSuccessListener { documentReference ->
                println("Task added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                println("Error adding task: $e")
            }
    }
    else
    {
        println("Wrong user ID")
    }
}