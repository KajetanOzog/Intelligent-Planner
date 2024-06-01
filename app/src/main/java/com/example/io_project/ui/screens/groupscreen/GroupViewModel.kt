package com.example.io_project.ui.screens.groupscreen

import androidx.compose.ui.graphics.vector.Group
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.dataclasses.Group
import com.example.io_project.datamanagement.getUserNameFromUID
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class GroupViewModel(
    groupJSON: String
) : ViewModel() {
    var group: Group = Group()
    var members = ArrayList<String>()

    init {
        group = Gson().fromJson(groupJSON, Group::class.java)
        viewModelScope.launch {
            for (member in group.groupMembers) {
                members.add(getUserNameFromUID(member))
            }
        }
    }


}