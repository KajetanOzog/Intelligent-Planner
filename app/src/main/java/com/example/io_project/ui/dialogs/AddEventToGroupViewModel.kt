package com.example.io_project.ui.dialogs

import addEventToGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.Constants.CORRECT_DATA
import com.example.io_project.Constants.GROUP_COLOR_HEX
import com.example.io_project.Constants.INCORRECT_DATA
import com.example.io_project.Constants.MISSING_DATA
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.EventPriority
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEventToGroupViewModel @Inject constructor(

) : AddEventBaseViewModel() {


    fun eventAddedSuccessfully(groupID: String): String {
        val errorMessage = necessaryArgumentsProvided()
        if (errorMessage == MISSING_DATA) {
            addEvent(groupID)
        }
        return errorMessage
    }


    private fun addEvent(groupID: String) {
        viewModelScope.launch {
            addEventToGroup(
                groupID = groupID,
                event = getEvent()
            )
        }
    }

    fun necessaryArgumentsProvided(): String {
        return if ((eventState.value.name == "") || (eventState.value.date == "")) {
            MISSING_DATA
        }
        else if ((eventState.value.time >= eventState.value.endTime) && (eventState.value.endTime != "")) {
            INCORRECT_DATA
        }
        else CORRECT_DATA
    }



}