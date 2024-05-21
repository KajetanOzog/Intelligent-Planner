package com.example.io_project.ui.screens.dialogs

import addEventToFirestore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.dataclasses.Event
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
) : ViewModel() {
    val eventState = MutableStateFlow(Event())
    val _eventState: StateFlow<Event> = eventState.asStateFlow()

    fun getEvent(): Event {
        return eventState.value
    }

    fun addEvent() {
        viewModelScope.launch {
            Firebase.auth.currentUser?.let {
                addEventToFirestore(
                    userID = it.uid,
                    event = getEvent(),
                    isRegular = true
                )
            }

        }
    }

    val _changeName: (String) -> Unit = { it -> changeName(it) }
    fun changeName(newName: String) {
        eventState.update { currentState -> currentState.copy(name = newName) }
    }

    val _changeCategory: (String) -> Unit = { it -> changeCategory(it) }
    fun changeCategory(newCategory: String) {
        eventState.update { currentState -> currentState.copy(category = newCategory) }
    }

    val _changeColor: (String) -> Unit = { it -> changeColor(it) }
    fun changeColor(newColor: String) {
        eventState.update { currentState -> currentState.copy(color = newColor) }
    }

    val _changePlace: (String) -> Unit = { it -> changePlace(it) }
    fun changePlace(newPlace: String) {
        eventState.update { currentState -> currentState.copy(place = newPlace) }
    }

    val _changeTime: (String) -> Unit = { it -> changeTime(it) }
    fun changeTime(newTime: String) {
        eventState.update { currentState -> currentState.copy(time = newTime) }
    }

    val _changeDate: (String) -> Unit = { it -> changeDate(it) }
    fun changeDate(newDate: String) {
        eventState.update { currentState -> currentState.copy(date = newDate) }
    }

    val _changeEndDate: (String) -> Unit = { it -> changeEndDate(it) }
    fun changeEndDate(newEndDate: String) {
        eventState.update { currentState -> currentState.copy(endDate = newEndDate) }
    }

    val _changeWeekly: (Boolean) -> Unit = { it -> changeWeekly(it) }
    fun changeWeekly(newWeekly: Boolean) {
        eventState.update { currentState -> currentState.copy(weekly = newWeekly) }
    }

    val _changeReminder: (Boolean) -> Unit = { it -> changeReminder(it) }
    fun changeReminder(newReminder: Boolean) {
        eventState.update { currentState -> currentState.copy(reminder = newReminder) }
    }

    val _changeAlarm: (Boolean) -> Unit = { it -> changeAlarm(it) }
    fun changeAlarm(newAlarm: Boolean) {
        eventState.update { currentState -> currentState.copy(alarm = newAlarm) }
    }

    val _changeReminderTime: (String) -> Unit = { it -> changeReminderTime(it) }
    fun changeReminderTime(newReminderTime: String) {
        eventState.update { currentState -> currentState.copy(reminderTime = newReminderTime) }
    }

    val _changeVisible: (Boolean) -> Unit = { it -> changeVisible(it) }
    fun changeVisible(newVisible: Boolean) {
        eventState.update { currentState -> currentState.copy(visible = newVisible) }
    }

    val _changeDescription: (String) -> Unit = { it -> changeDescription(it) }
    fun changeDescription(newDescription: String) {
        eventState.update { currentState -> currentState.copy(description = newDescription) }
    }

}