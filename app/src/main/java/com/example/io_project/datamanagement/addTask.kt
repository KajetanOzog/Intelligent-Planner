package com.example.io_project.datamanagement

import com.example.io_project.dataclasses.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth

suspend fun addTaskToFirestore(userID: String, task: Task) {
    val firestore = FirebaseFirestore.getInstance()
    val userDocumentRef = firestore.collection("users").document(userID)

    val taskData = hashMapOf<String, Any>(
        "name" to task.name,
        "completed" to task.completed,
        "daysCount" to task.daysCount,
        "daysCounter" to task.daysCounter,
        "maxStreak" to task.maxStreak
    )

    userDocumentRef.collection("tasks").add(taskData)
}