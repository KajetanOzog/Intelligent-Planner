package com.example.io_project.ui.screens.groupscreen

import android.util.Log
import androidx.compose.ui.graphics.vector.Group
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.Constants
import com.example.io_project.Constants.DATE_FORMATTER_PATTERN
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.Group
import com.example.io_project.datamanagement.fetchEvents
import com.example.io_project.datamanagement.fetchGroup
import com.example.io_project.datamanagement.getUserNameFromUID
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.gson.Gson
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel(assistedFactory = GroupViewModel.GroupViewModelFactory::class)
class GroupViewModel @AssistedInject constructor(
    @Assisted val groupJSON: String
) : ViewModel() {
    @AssistedFactory
    interface GroupViewModelFactory {
        fun create(groupJSON: String): GroupViewModel
    }

    var group: Group = Group()
    var members = ArrayList<String>()

    var eventsListState = mutableListOf<Event>()
    var dateState = MutableStateFlow(LocalDate.now())

    init {
        group = Gson().fromJson(groupJSON, Group::class.java)
        refreshData()
    }

    fun getDateString(): String =
        dateState.value.format(DateTimeFormatter.ofPattern(DATE_FORMATTER_PATTERN, Locale.ENGLISH))

    fun changeDate(newDate: String) {
        dateState.update {
            LocalDate.parse(
                newDate,
                DateTimeFormatter.ofPattern(DATE_FORMATTER_PATTERN, Locale.ENGLISH)
            )
        }
        updateEvents()
    }


    fun currentUserIsAdmin(): Boolean {
        return Firebase.auth.currentUser?.uid == group.ownerID
    }

    private fun updateEvents() {
        runBlocking {
            Firebase.auth.currentUser?.let {
                Log.d("GroupVM", "$dateState")
                eventsListState = fetchGroup(group.groupID)?.events?.toMutableList()
                    ?: emptyList<Event>().toMutableList()

                eventsListState = eventsListState.filter {
                    it.date == getDateString()
                }.toMutableList()
                Log.d("GroupVM", "$eventsListState")
            }
        }


    }

    fun getPreviousDay() {
        dateState.update { it.minusDays(1) }
        updateEvents()
    }

    fun getNextDay() {
        dateState.update { it.plusDays(1) }
        updateEvents()
    }

    fun refreshData() {
        runBlocking {
            updateEvents()
            members.clear()
            for (member in group.groupMembers) {
                members.add(getUserNameFromUID(member))
            }
        }
    }

}