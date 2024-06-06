package com.example.io_project.datamanagement
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.tasks.await


suspend fun importUserFile(userId: String, jsonData: String) {
    val db = Firebase.firestore
    val userDocRef = db.collection("users").document(userId)

    try {
        val dataStructure = Gson().fromJson(jsonData, Map::class.java)

        (dataStructure["Events"] as? Map<*, *>)?.get("Regular")?.let { regular ->
            (regular as? Map<*, *>)?.forEach { (day, events) ->
                userDocRef.update("regular.$day", events).await()
            }
        }

        (dataStructure["Events"] as? Map<*, *>)?.get("Nonregular")?.let { nonregular ->
            userDocRef.update("nonregular", nonregular).await()
        }

        dataStructure["Tasks"]?.let { tasks ->
            userDocRef.update("tasks", tasks).await()
        }

        (dataStructure["Goals"] as? Map<*, *>)?.let { goalsMap ->
            goalsMap["Completed"]?.let { completed ->
                userDocRef.update("goals.completed", completed).await()
            }
            goalsMap["Unfinished"]?.let { unfinished ->
                userDocRef.update("goals.unfinished", unfinished).await()
            }
        }
    } catch (e: Exception) {
        println("An error occurred: ${e.message}")
    }
}