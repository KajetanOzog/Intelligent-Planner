package com.example.io_project.ui.dialogs

import addEventToGroup
import androidx.lifecycle.viewModelScope
import com.example.io_project.Constants.CORRECT_DATA
import com.example.io_project.Constants.INCORRECT_DATA
import com.example.io_project.Constants.MISSING_DATA
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEventToGroupViewModel @Inject constructor(

) : AddEventBaseViewModel() {


    fun eventAddedSuccessfully(groupID: String): String {
        val errorMessage = necessaryArgumentsProvided()
        if (errorMessage == CORRECT_DATA) {
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