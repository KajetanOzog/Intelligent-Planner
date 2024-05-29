package com.example.io_project.datamanagement

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun addUserToGroup(email: String, groupID: String)
{
    val firestore = FirebaseFirestore.getInstance()

    val metadataRef = firestore.collection("metadata").document("user_email")
    val document = metadataRef.get().await()

    if (document.exists())
    {
        val userID = document.getString("$email.uid")
        if (userID != null)
        {
            val userRef = firestore.collection("users").document(userID)
            userRef.update("groups", FieldValue.arrayUnion(groupID))

            val groupRef = firestore.collection("metadata").document("groups")
            groupRef.update("$groupID.groupMembers", FieldValue.arrayUnion(userID))

            println("User added to group.")
        } else {
            println("No user found with the provided email.")
        }
    } else {
        println("No document found in metadata/user_email.")
    }
}