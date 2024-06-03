package com.example.io_project.ui.dialogs

import addEventToGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.Constants.GROUP_COLOR_HEX
import com.example.io_project.dataclasses.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEventToGroupViewModel @Inject constructor(

) : ViewModel() {

    val eventState = MutableStateFlow(Event(color = GROUP_COLOR_HEX))

    fun getEvent(): Event {
        return eventState.value
    }


    fun eventAddedSuccessfully(groupID: String): Boolean {
        return if (necessaryArgumentsProvided()) {
            addEvent(groupID)
            true
        } else {
            false
        }
    }


    private fun addEvent(groupID: String) {
        viewModelScope.launch {
            addEventToGroup(
                groupID = groupID,
                event = getEvent()
            )
        }
    }

    private fun necessaryArgumentsProvided(): Boolean {
        return (eventState.value.name != "") && (eventState.value.date != "")
    }


    val _changeName: (String) -> Unit = { it -> changeName(it) }
    private fun changeName(newName: String) {
        eventState.update { currentState -> currentState.copy(name = newName) }
    }

    val _changeCategory: (String) -> Unit = { it -> changeCategory(it) }
    private fun changeCategory(newCategory: String) {
        eventState.update { currentState -> currentState.copy(category = newCategory) }
    }

    val _changePlace: (String) -> Unit = { it -> changePlace(it) }
    private fun changePlace(newPlace: String) {
        eventState.update { currentState -> currentState.copy(place = newPlace) }
    }

    val _changeTime: (String) -> Unit = { it -> changeTime(it) }
    private fun changeTime(newTime: String) {
        eventState.update { currentState -> currentState.copy(time = newTime) }
    }

    val _changeDate: (String) -> Unit = { it -> changeDate(it) }
    private fun changeDate(newDate: String) {
        eventState.update { currentState -> currentState.copy(date = newDate) }
    }

    val _changeEndDate: (String) -> Unit = { it -> changeEndDate(it) }
    private fun changeEndDate(newEndDate: String) {
        eventState.update { currentState -> currentState.copy(endDate = newEndDate) }
    }

    val _changeDescription: (String) -> Unit = { it -> changeDescription(it) }
    private fun changeDescription(newDescription: String) {
        eventState.update { currentState -> currentState.copy(description = newDescription) }
    }


}