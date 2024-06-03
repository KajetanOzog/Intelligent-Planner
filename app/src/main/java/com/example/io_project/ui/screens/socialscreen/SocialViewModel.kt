package com.example.io_project.ui.screens.socialscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.io_project.dataclasses.Group
import com.example.io_project.datamanagement.fetchFriends
import com.example.io_project.datamanagement.fetchUserGroups
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
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
        getFriendsList()
        Log.d("SocialViewModel", "Fetched groups: $groups")
        Log.d("SocialViewModel", "Fetched friends: $friends")
    }

    fun getGroupsList() {
         runBlocking {
            Firebase.auth.currentUser?.let {
                groups = fetchUserGroups(it.uid) ?: emptyList()
                groupCount = groups.size
            }
        }
    }

    fun getFriendsList() {
        runBlocking {
            Firebase.auth.currentUser?.let {
                friends = fetchFriends(it.uid) ?: emptyList()
                friendCount = friends.size
            }
        }
    }
}