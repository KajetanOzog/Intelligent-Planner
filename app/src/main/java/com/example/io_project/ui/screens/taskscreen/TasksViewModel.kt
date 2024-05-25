package com.example.io_project.ui.screens.taskscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.dataclasses.Task
import com.example.io_project.datamanagement.getTasks
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(

): ViewModel() {
    var tasks: List<Task> = emptyList()

    init{
        getTasksToList()
    }

    fun getTasksToList() {
        viewModelScope.launch {
            Firebase.auth.currentUser?.let {
                tasks = getTasks(it.uid) ?: emptyList()
            }
        }
    }
}