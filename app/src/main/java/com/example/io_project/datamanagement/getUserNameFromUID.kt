package com.example.io_project.datamanagement

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

suspend fun getUserNameFromUID(userID: String): String {
    val firestore = FirebaseFirestore.getInstance()
    return firestore.collection("metadata").document("email_user.$userID")
        .get().await().data?.get("uid")?.toString() ?: ""
}