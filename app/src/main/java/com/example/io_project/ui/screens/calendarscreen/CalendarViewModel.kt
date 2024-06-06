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
import javax.inject.Inject


@HiltViewModel(assistedFactory = CalendarViewModel.CalendarViewModelFactory::class)
class CalendarViewModel @AssistedInject constructor(
    @Assisted val allEvents: State<Boolean>
) : ViewModel() {
    var eventsListState = mutableListOf<Event>()
    val dateState = MutableStateFlow(LocalDate.now())

    @AssistedFactory
    interface CalendarViewModelFactory {
        fun create(allEvents: State<Boolean>): CalendarViewModel
    }


    init {
        updateEvents()
    }

    fun getDateString(): String =
        dateState.value.format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy"))

    fun changeDate(newDate: String) {
        dateState.update {
            LocalDate.parse(
                newDate,
                DateTimeFormatter.ofPattern("EEE, MMM d yyyy")
            )
        }
        updateEvents()
    }


    private fun updateEvents() {
        runBlocking {
            Firebase.auth.currentUser?.let {
                eventsListState =
                    if (allEvents.value) {
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
        Log.d("CVM", getDateString())
        updateEvents()
    }

    fun getNextDay() {
        dateState.update { it.plusDays(1) }
        Log.d("CVM", getDateString())
        updateEvents()
    }

    fun refreshData() {
        updateEvents()
    }
}