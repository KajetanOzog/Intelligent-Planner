package com.example.io_project.datamanagement

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

suspend fun addUserToGroup(email: String, groupID: String) {
    val firestore = FirebaseFirestore.getInstance()
    var userID = ""

    // Retrieve the user ID associated with the provided email
    firestore.collection("metadata").document("user_email")
        .get().addOnSuccessListener { document ->
            // Retrieve the user data using the email
            val userData = document.data?.get(email) as? Map<String, String>
            userID = userData?.get("uid") ?: ""

            if (userID != "") {
                val userRef = firestore.collection("users").document(userID)
                // Update the user's groups list
                userRef.update("groups", FieldValue.arrayUnion(groupID))

                // Get reference to the group document
                val groupRef = firestore.collection("metadata").document("groups")
                // Update the group's members list
                groupRef.update("$groupID.groupMembers", FieldValue.arrayUnion(userID))

                Log.d("UserToGroup", "User added to group.")
            } else {
                Log.d("UserToGroup", "No user found with the provided email address.")
            }
        }.addOnFailureListener {
            Log.d("UserToGroup", "${it.message}")
        }
}
