package com.example.io_project.datamanagement

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun getUserDocument(userID: String): DocumentSnapshot? {
    val firestore = FirebaseFirestore.getInstance()
    val userDocumentRef = firestore.collection("users").document(userID)

    return try {
        val documentSnapshot = userDocumentRef.get().await()
        documentSnapshot

    } catch (e: Exception) {
        println("Błąd podczas pobierania dokumentu użytkownika: ${e.message}")
        e.printStackTrace()
        null
    }
}