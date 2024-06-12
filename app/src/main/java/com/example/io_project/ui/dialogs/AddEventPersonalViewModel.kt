package com.example.io_project.ui.dialogs

import addEventToFirestore
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.io_project.Constants.CORRECT_DATA
import com.example.io_project.Constants.INCORRECT_DATA
import com.example.io_project.Constants.MISSING_DATA
import com.example.io_project.dataclasses.Alarm
import com.example.io_project.notifications.AlarmScheduler
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddEventPersonalViewModel @Inject constructor(
) : AddEventBaseViewModel() {


    fun eventAddedSuccessfully(alarmScheduler: AlarmScheduler): String {
        val errorMessage = necessaryArgumentsProvided()
        if (errorMessage == CORRECT_DATA) {
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
        }
        return errorMessage
    }


    private fun addEvent() {
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

    fun necessaryArgumentsProvided(): String {
        return if ((eventState.value.alarm && !eventState.value.reminder) ||
            (eventState.value.reminder xor (eventState.value.reminderTime != "")) ||
            (eventState.value.name == "") || (eventState.value.date == "")) {
            MISSING_DATA
        }
        else if ((eventState.value.time >= eventState.value.endTime) && (eventState.value.endTime != "")) {
            INCORRECT_DATA
        }
        else CORRECT_DATA
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


}