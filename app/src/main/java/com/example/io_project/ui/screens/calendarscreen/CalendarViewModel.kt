package com.example.io_project.ui.screens.calendarscreen


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.Constants
import com.example.io_project.Constants.DATE_FORMATTER_PATTERN
import com.example.io_project.dataclasses.Event
import com.example.io_project.datamanagement.fetchAllUserEvents
import com.example.io_project.datamanagement.fetchEvents
import com.example.io_project.user.Settings
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class CalendarViewModel @Inject constructor(
) : ViewModel() {
    var allEvents: Boolean = false
    var eventsListState = mutableListOf<Event>()
    val dateState = MutableStateFlow(LocalDate.now())


    init {
        updateEvents()
    }

    fun getDateString(): String =
        dateState.value.format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy", Locale.ENGLISH))

    fun changeDate(newDate: String) {
        dateState.update {
            LocalDate.parse(
                newDate,
                DateTimeFormatter.ofPattern("EEE, MMM d yyyy", Locale.ENGLISH)
            )
        }
        updateEvents()
    }


    private fun updateEvents() {

        runBlocking {
            Firebase.auth.currentUser?.let {
                eventsListState =
                    if (allEvents) {
                        fetchAllUserEvents(it.uid, getDateString())?.toMutableList()
                            ?: emptyList<Event>().toMutableList()
                    }  else {
                        fetchEvents(it.uid, getDateString())?.toMutableList()
                            ?: emptyList<Event>().toMutableList()
                    }
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
        updateEvents()
    }
}