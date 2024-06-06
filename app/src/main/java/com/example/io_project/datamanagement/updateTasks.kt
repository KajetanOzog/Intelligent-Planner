package com.example.io_project.datamanagement
import android.util.Log
import com.example.io_project.dataclasses.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.format.DateTimeFormatter

suspend fun updateTasks(tasks: List<Task>, userID: String) {
    val firestore = FirebaseFirestore.getInstance()
    val userDocumentRef = firestore.collection("users").document(userID)
    val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy MM dd"))

    for (task in tasks) {
        Log.d("UpdateTask", "$task, $today")
        if (task.lastCheck != today && task.completed) {
            task.doneCount++
            task.lastCheck = today
        }
    }
    try {
        userDocumentRef.update("tasks", tasks).await()
        println("Tasks updated successfully.")
    } catch (e: Exception) {
        println("Error when updating tasks: ${e.message}")
        e.printStackTrace()
    }
}
