package com.example.io_project.datamanagement

import com.example.io_project.dataclasses.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.tasks.await

suspend fun addTaskToFirestore(userID: String, task: Task) {
    val firestore = FirebaseFirestore.getInstance()
    val userDocumentRef = firestore.collection("users").document(userID)
    userDocumentRef.update("tasks", FieldValue.arrayUnion(task)).await()
}