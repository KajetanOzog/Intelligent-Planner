package com.example.io_project.ui.dialogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.datamanagement.addFriends
import com.example.io_project.datamanagement.addUserToGroup
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserToGroupViewModel @Inject constructor(

) : ViewModel() {
    fun addUserToGroupByEmail(email: String, groupID: String) {
        viewModelScope.launch {
            addUserToGroup(email, groupID)
        }
    }
}