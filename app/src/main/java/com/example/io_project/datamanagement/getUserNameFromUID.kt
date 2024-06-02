package com.example.io_project.datamanagement

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

suspend fun getUserNameFromUID(userID: String): String {
    val firestore = FirebaseFirestore.getInstance()
    var returnUserName = ""
    try {
        val emailData = firestore.collection("metadata")
            .document("user_email").get().await().data as Map<String,Map<String, Any>>
        for (key in emailData) {
            if (key.value["uid"] == userID)
                returnUserName = key.value["userName"].toString()
        }
    } catch (e: Exception) {
        Log.e("UserNameFromUID", "${e.message}")
    }

    return returnUserName
}