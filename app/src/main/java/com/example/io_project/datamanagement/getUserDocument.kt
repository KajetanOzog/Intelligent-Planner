package com.example.io_project.datamanagement

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun getUserDocument(userID: String): DocumentSnapshot? {
    val firestore = FirebaseFirestore.getInstance()
    // Reference to the document of the specified user
    val userDocumentRef = firestore.collection("users").document(userID)

    return try {
        // Retrieve the user document from Firestore and await its completion
        val documentSnapshot = userDocumentRef.get().await()
        documentSnapshot

    } catch (e: Exception) {
        Log.e("getUserDocument", "Error loading user document: ${e.message}")
        null
    }
}
