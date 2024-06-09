package com.example.io_project.datamanagement

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

suspend fun addFriends(userId: String, email: String) {
    val firestore = FirebaseFirestore.getInstance()
    var secondUserID = ""

    // Get the user ID associated with the provided email
    firestore.collection("metadata").document("user_email")
        .get().addOnSuccessListener { document ->
            // Retrieve the user data using the email
            val userData = document.data?.get(email) as? Map<String, String>
            secondUserID = userData?.get("uid") ?: ""

            if (secondUserID != "") {
                // Get references to both users' documents
                val userRef = firestore.collection("users").document(userId)
                val secondUserRef = firestore.collection("users").document(secondUserID)

                // Update both users' friends lists
                userRef.update("friends", FieldValue.arrayUnion(secondUserID))
                secondUserRef.update("friends", FieldValue.arrayUnion(userId))

                Log.d("AddFriends", "Users added to each other's friends list successfully.")
            } else {
                Log.d("AddFriends", "No user found with the provided email address.")
            }
        }.addOnFailureListener {
            Log.d("AddFriends", "${it.message}")
        }
}
