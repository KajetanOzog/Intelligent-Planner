package com.example.io_project.datamanagement

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun getGroupName(groupID: String): String {
    val firestore = FirebaseFirestore.getInstance()
    val documentSnapshot = firestore.collection("metadata")
        .document("groups").get().await()
    return documentSnapshot.getString("$groupID.groupName") ?: ""
}