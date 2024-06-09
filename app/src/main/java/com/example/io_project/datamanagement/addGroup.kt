package com.example.io_project.datamanagement
import android.util.Log
import com.example.io_project.dataclasses.Group
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.util.UUID

// Function to add a group to Firestore, with an empty groupID provided
suspend fun addGroup(group: Group, userID: String) {
    // Generate a unique group ID
    val groupId = UUID.randomUUID().toString()
    group.groupID = groupId

    val firestore = FirebaseFirestore.getInstance()
    val metadataRef = firestore.collection("metadata").document("groups")

    val groupData = mapOf(
        groupId to group
    )

    try {
        // Update the metadata document with the new group data
        metadataRef.set(groupData, SetOptions.merge())
        Log.d("AddGroup", "2: users/${userID}/groups")

        // Add the group ID to the user's document
        val usersDocumentRef = firestore.collection("users").document(userID)
        usersDocumentRef.update("groups", FieldValue.arrayUnion(groupId))
        Log.d("AddGroup", "Group added successfully.")
    } catch (e: Exception) {
        Log.d("AddGroup", "Error when adding group: ${e.message}")
    }
}
