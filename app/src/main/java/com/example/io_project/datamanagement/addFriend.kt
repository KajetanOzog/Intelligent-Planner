package com.example.io_project.datamanagement
import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

suspend fun addFriends(userId: String, email: String) {
    val firestore = FirebaseFirestore.getInstance()
    var secondUserID = ""
    firestore.collection("metadata").document("user_email")
        .get().addOnSuccessListener { document ->
            val userData = document.data?.get(email) as? Map<String, String>
            secondUserID = userData?.get("uid") ?: ""
            if (secondUserID != "") {
                val userRef = firestore.collection("users").document(userId)
                val secondUserRef = firestore.collection("users").document(secondUserID)
                userRef.update("friends", FieldValue.arrayUnion(secondUserID))
                secondUserRef.update("friends", FieldValue.arrayUnion(userId))
                println("Users added to each other's friends list successfully.")
            } else {
                println("No user found with the provided email address.")
            }
        }.addOnFailureListener {
            Log.d("AddFriend", "${it.message}")
            println("No document found for provided email address.")
        }
}