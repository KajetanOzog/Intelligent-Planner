package com.example.io_project.ui.dialogs

import addEventToFirestore
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.Constants.DEFAULT_COLOR_HEX
import com.example.io_project.dataclasses.Alarm
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.EventPriority
import com.example.io_project.notifications.AlarmScheduler
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
) : ViewModel() {
    val eventState = MutableStateFlow(
        Event(
            color = DEFAULT_COLOR_HEX,
            priority = EventPriority.MEDIUM,
            category = "Inne"
        )
    )

    fun getEvent(): Event {
        return eventState.value
    }


    fun eventAddedSuccessfully(alarmScheduler: AlarmScheduler): Boolean {
        if (necessaryArgumentsProvided()) {
            addEvent()
            if (eventState.value.reminder) {
                val alarm = Alarm(
                    message = eventState.value.name,
                    time = LocalDateTime.of(
                        LocalDate.parse(
                            eventState.value.date,
                            DateTimeFormatter.ofPattern("EEE, MMM d yyyy")
                        ),
                        LocalTime.parse(eventState.value.reminderTime)
                    ),
                    sound = eventState.value.alarm
                )
                if (eventState.value.weekly) {
                    alarmScheduler.scheduleWeekly(alarm)
                    Log.d("AddActDia", "Scheduling weekly event")
                } else {
                    alarmScheduler.scheduleSingle(alarm)
                    Log.d("AddActDia", "Scheduling single event")
                }
            }
            return true
        }
        return false
    }


    fun addEvent() {
        viewModelScope.launch {
            Firebase.auth.currentUser?.let {
                addEventToFirestore(
                    userID = it.uid,
                    event = getEvent(),
                    isRegular = eventState.value.weekly
                )
            }

        }
    }

    fun necessaryArgumentsProvided(): Boolean {
        return (eventState.value.name != "") && (eventState.value.date != "")
                && !(eventState.value.reminder xor (eventState.value.reminderTime != ""))
                && !(eventState.value.alarm xor eventState.value.reminder)
                && (eventState.value.time < eventState.value.endTime)
    }


    val _changeName: (String) -> Unit = { it -> changeName(it) }
    fun changeName(newName: String) {
        eventState.update { currentState -> currentState.copy(name = newName) }
    }

    val _changeCategory: (String) -> Unit = { it -> changeCategory(it) }
    fun changeCategory(newCategory: String) {
        eventState.update { currentState -> currentState.copy(category = newCategory) }
    }

    val _changePriority: (String) -> Unit = { it -> changePriority(it) }
    fun changePriority(newPriorityString: String) {
        val priorityMap: Map<String, EventPriority> = mapOf(
            "Niski" to EventPriority.LOW,
            "Średni" to EventPriority.MEDIUM,
            "Wysoki" to EventPriority.HIGH
        )
        eventState.update { currentState -> currentState.copy(priority = priorityMap[newPriorityString]!!) }
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

    val _changeEndTime: (String) -> Unit = { it -> changeEndTime(it) }
    fun changeEndTime(newEndTime: String) {
        eventState.update { currentState -> currentState.copy(endTime = newEndTime) }
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