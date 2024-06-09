package com.example.io_project.datamanagement

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

// Function to asynchronously retrieve the name of a group from Firestore using its ID
suspend fun getGroupName(groupID: String): String {
    val firestore = FirebaseFirestore.getInstance()
    // Retrieve the group document from Firestore and await its completion
    val documentSnapshot = firestore.collection("metadata")
        .document("groups").get().await()
    return documentSnapshot.getString("$groupID.groupName") ?: "" // If group name is null, return empty string
}
