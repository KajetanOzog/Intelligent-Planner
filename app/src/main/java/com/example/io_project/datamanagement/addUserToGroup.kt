package com.example.io_project.datamanagement
import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore


suspend fun addUserToGroup(email: String, groupID: String) {
    val firestore = FirebaseFirestore.getInstance()
    var userID = ""
    firestore.collection("metadata").document("user_email")
        .get().addOnSuccessListener { document ->
            val userData = document.data?.get(email) as? Map<String, String>
            userID = userData?.get("uid") ?: ""
            if (userID != "") {
                val userRef = firestore.collection("users").document(userID)
                userRef.update("groups", FieldValue.arrayUnion(groupID))

                val groupRef = firestore.collection("metadata").document("groups")
                groupRef.update("$groupID.groupMembers", FieldValue.arrayUnion(userID))

                println("User added to group.")
            } else {
                println("No user found with the provided email address.")
            }
        }.addOnFailureListener {
            Log.d("UserToGroup", "${it.message}")
            println("No document found for provided email address.")
        }
}