package com.example.io_project.ui.screens.taskscreen

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.dataclasses.Task
import com.example.io_project.datamanagement.getTasks
import com.example.io_project.datamanagement.updateTasks
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(

): ViewModel() {
    var tasks: List<Task> = emptyList()
    var size: Int = 0
    init{
        getTasksToList()
        Log.d("TasksViewModel", "Pobrane zadania: $tasks")
    }

    fun acceptChanges() {
        Firebase.auth.currentUser?.let{
            viewModelScope.launch {
                updateTasks(tasks, it.uid)
            }
        }
    }

    fun getTasksToList() {
        runBlocking {
            Firebase.auth.currentUser?.let {
                tasks = getTasks(it.uid) ?: emptyList()
                size = tasks.size
            }
        }
    }
}