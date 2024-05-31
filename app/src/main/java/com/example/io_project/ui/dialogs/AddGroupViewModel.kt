package com.example.io_project.ui.dialogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.dataclasses.Group
import com.example.io_project.datamanagement.addGroup
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddGroupViewModel @Inject constructor(

): ViewModel() {
    val groupState = MutableStateFlow(Group())

    fun getGroup(): Group {
        return groupState.value
    }
    fun addNewGroup() {
        viewModelScope.launch {
            Firebase.auth.currentUser?.let {
                val userID = it.uid
                groupState.update { currentState -> currentState.copy(ownerID = userID) }
                groupState.update { currentState -> currentState.copy(groupMembers = listOf(userID)) }
                addGroup(getGroup(), userID)
            }
        }
    }

    val _changeName: (String) -> Unit = { it -> changeName(it) }
    fun changeName(newName: String) {
        groupState.update { currentState -> currentState.copy(groupName = newName) }
    }
}