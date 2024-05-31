package com.example.io_project.ui.dialogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.dataclasses.Task
import com.example.io_project.datamanagement.addGoalToFirestore
import com.example.io_project.datamanagement.addTaskToFirestore
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
) : ViewModel() {
    val taskState = MutableStateFlow(Task())
    val _taskState: StateFlow<Task> = taskState.asStateFlow()

    fun getTask(): Task {
        return taskState.value
    }

    fun addTask() {
        viewModelScope.launch {
            Firebase.auth.currentUser?.let {
                addTaskToFirestore(
                    userID = it.uid,
                    task = getTask()
                )
            }
        }
    }

    val _changeName: (String) -> Unit = { it -> changeName(it) }
    fun changeName(newName: String) {
        taskState.update { currentState -> currentState.copy(name = newName) }
    }

}