package com.example.io_project.datamanagement

import android.util.Log
import com.example.io_project.dataclasses.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Function to update tasks in Firestore for a given user
suspend fun updateTasks(tasks: List<Task>, userID: String) {
    val firestore = FirebaseFirestore.getInstance()
    val userDocumentRef = firestore.collection("users").document(userID)
    // Get current date
    val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy MM dd"))

    // Iterate through tasks
    for (task in tasks) {
        Log.d("UpdateTask", "$task, $today")
        // Check if task was completed today
        if (task.lastCheck != today && task.completed) {
            // Increment doneCount and update lastCheck
            task.doneCount++
            task.lastCheck = today
        }
    }

    try {
        // Update Firestore with updated tasks
        userDocumentRef.update("tasks", tasks).await()
        Log.d("updateTasks", "Tasks updated successfully.")
    } catch (e: Exception) {
        Log.e("updateTasks", "Error when updating tasks: ${e.message}")
    }
}
