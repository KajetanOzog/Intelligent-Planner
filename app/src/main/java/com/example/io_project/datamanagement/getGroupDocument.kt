package com.example.io_project.datamanagement

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

// Function to asynchronously retrieve the group document from Firestore
suspend fun getGroupDocument(): DocumentSnapshot? {
    val firestore = FirebaseFirestore.getInstance()
    return try {
        // Retrieve group document from Firestore and await its completion
        firestore.collection("metadata").document("groups").get().await()
    } catch (e: Exception) {
        Log.d("GetGroupDocument", "${e.message}")
        null
    }
}
