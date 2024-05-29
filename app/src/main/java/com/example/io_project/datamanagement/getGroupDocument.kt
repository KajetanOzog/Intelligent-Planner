package com.example.io_project.datamanagement

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun getGroupDocument(groupID: String): DocumentSnapshot? {
    val firestore = FirebaseFirestore.getInstance()
    return firestore.collection("metadata").document("groups.$groupID").get().await()
}