package com.example.io_project.datamanagement

import android.util.Log
import com.example.io_project.dataclasses.Goal
import com.example.io_project.dataclasses.Group
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await


suspend fun fetchFriends(userID: String): List<String>? {
    val firestore = FirebaseFirestore.getInstance()
    val returnList = ArrayList<String>()

    try {
        val documentSnapshot = getUserDocument(userID)
        if (documentSnapshot != null && documentSnapshot.exists()) {
            @Suppress("UNCHECKED_CAST")
            val friendIDs = documentSnapshot.get("friends") as? List<String> ?: emptyList()
            Log.d("FetchFriends", "FriendIDs: $friendIDs")

            for (friendID in friendIDs) {
                val querySnapshot = firestore.collection("metadata").document("user_email").get().await()
                if (querySnapshot != null && querySnapshot.exists()) {
                    @Suppress("UNCHECKED_CAST")
                    val userEmailData = querySnapshot.data?.values as? List<Map<String, Any>>
                    if (userEmailData != null) {
                        for (data in userEmailData) {
                            if (data["uid"] == friendID) {
                                val username = data["userName"]?.toString()
                                if (username != null) {
                                    returnList.add(username)
                                }
                            }
                        }
                    }
                }
            }
            return returnList
        } else {
            println("User document does not exist or could not be retrieved.")
        }
    } catch (e: Exception) {
        println("Error fetching friends: ${e.message}")
        e.printStackTrace()
    }
    return null
}
