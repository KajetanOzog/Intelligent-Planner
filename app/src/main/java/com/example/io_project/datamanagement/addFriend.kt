package com.example.io_project.datamanagement
import android.os.Bundle
import android.widget.Button
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

suspend fun addFriends(userId: String, email: String) {
    val firestore = FirebaseFirestore.getInstance()

    val metadataRef = firestore.collection("metadata").document("user_email")
    val document = metadataRef.get().await()

    if (document.exists())
    {
        val secondUserId = document.getString(email)
        if (secondUserId != null)
        {
            val userRef = firestore.collection("users").document(userId)
            val secondUserRef = firestore.collection("users").document(secondUserId)
            userRef.update("friends", FieldValue.arrayUnion(secondUserId))
            secondUserRef.update("friends", FieldValue.arrayUnion(userId))
            println("Users added to each other's friends list successfully.")
        } else {
            println("No user found with the provided email.")
        }
    } else {
        println("No document found in metadata/user_email.")
    }
}