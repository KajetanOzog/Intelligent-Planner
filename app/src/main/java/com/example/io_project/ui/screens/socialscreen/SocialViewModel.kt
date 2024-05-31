package com.example.io_project.ui.screens.socialscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.dataclasses.Group
import com.example.io_project.datamanagement.fetchGroups
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SocialViewModel @Inject constructor(

): ViewModel(){
    var groups: List<Group> = emptyList()
    var groupCount: Int = 0

    var friends: List<String> = emptyList()
    var friendCount: Int = 0
    init {
        getGroupsList()
        Log.d("SocialViewModel", "Fetched groups: $groups")
    }

    fun getGroupsList() {
        viewModelScope.launch {
            Firebase.auth.currentUser?.let {
                groups = fetchGroups(it.uid) ?: emptyList()
                groupCount = groups.size
            }
        }
    }

    fun getFriendsList() {
        viewModelScope.launch {
            Firebase.auth.currentUser?.let {

            }
        }
    }
}