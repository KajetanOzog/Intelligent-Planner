package com.example.io_project.ui.screens.homescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.Constants.DATE_FORMATTER_PATTERN
import com.example.io_project.dataclasses.Event
import com.example.io_project.datamanagement.fetchAllUserEvents
import com.example.io_project.datamanagement.fetchEvents
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    var allEvents: Boolean = false
    var todaysEvents = mutableListOf<Event>()
    init {
        refreshData()
    }

    fun getTodaysDateString() =
        LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMATTER_PATTERN))

    fun getTodaysEvents() {
        runBlocking {
            Firebase.auth.currentUser?.let {
                todaysEvents =
                    if (allEvents) {
                        fetchAllUserEvents(it.uid, getTodaysDateString())?.toMutableList()
                            ?: emptyList<Event>().toMutableList()
                    }  else {
                        fetchEvents(it.uid, getTodaysDateString())?.toMutableList()
                            ?: emptyList<Event>().toMutableList()
                    }
            }
        }
    }

    fun refreshData() {
        getTodaysEvents()
    }

}