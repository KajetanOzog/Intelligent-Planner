package com.example.io_project.ui.dialogs

import androidx.lifecycle.ViewModel
import com.example.io_project.Constants
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.EventPriority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

abstract class AddEventBaseViewModel(

): ViewModel() {
    val eventState = MutableStateFlow(
        Event(
            color = Constants.DEFAULT_COLOR_HEX,
            priority = EventPriority.MEDIUM,
            category = "Inne"
        )
    )

    fun getEvent(): Event {
        return eventState.value
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

    val _changeDescription: (String) -> Unit = { it -> changeDescription(it) }
    fun changeDescription(newDescription: String) {
        eventState.update { currentState -> currentState.copy(description = newDescription) }
    }
}