package com.example.io_project.ui.dialogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.dataclasses.Goal
import com.example.io_project.datamanagement.addGoalToFirestore
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
class AddGoalViewModel @Inject constructor(
): ViewModel() {
    val goalState = MutableStateFlow(Goal())
    val _goalState: StateFlow<Goal> = goalState.asStateFlow()

    fun getGoal(): Goal {
        return goalState.value
    }

    fun addGoal() {
        viewModelScope.launch {
            Firebase.auth.currentUser?.let {
                addGoalToFirestore(
                    userID = it.uid,
                    goal = getGoal(),
                    isCompleted = false
                )
            }
        }
    }

    val _changeName: (String) -> Unit = { it -> changeName(it) }
    fun changeName(newName: String) {
        goalState.update { currentState -> currentState.copy(name = newName) }
    }

    val _changeDeadline: (String) -> Unit = { it -> changeDeadline(it) }
    fun changeDeadline(newDeadline: String) {
        goalState.update { currentState -> currentState.copy(deadline = newDeadline) }
    }
}