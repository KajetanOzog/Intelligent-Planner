package com.example.io_project.datamanagement
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
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

            val userEmailData = firestore.collection("metadata")
                .document("user_email").get().await().data as Map<String,Map<String, Any>>
            Log.d("FetchFriends", "$userEmailData")

            for (friendID in friendIDs) {
                for (key in userEmailData) {
                    Log.d("FetchFriends", "$key")
                    if (key.value["uid"] == friendID) {
                        val username = key.value["userName"]?.toString()
                        if (username != null) {
                            returnList.add(username)
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
