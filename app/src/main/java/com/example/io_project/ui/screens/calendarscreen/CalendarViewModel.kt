package com.example.io_project.ui.screens.calendarscreen


import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.io_project.dataclasses.Event
import com.example.io_project.datamanagement.fetchEvents
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
class CalendarViewModel @Inject constructor(

): ViewModel() {
    var eventsListState = mutableListOf<Event>()
    val dateState = MutableStateFlow(LocalDate.now())

    init {
        Log.d("CalendarVM", "$dateState")
        updateEvents()
    }

    fun getDateString(): String = dateState.value.format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy"))
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
                eventsListState = fetchEvents(it.uid, getDateString())?.toMutableList() ?: emptyList<Event>().toMutableList()
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