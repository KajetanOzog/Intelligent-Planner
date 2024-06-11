package com.example.io_project.ui.dialogs

import addEventToGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.Constants.GROUP_COLOR_HEX
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

    fun necessaryArgumentsProvided(): Boolean {
        return (eventState.value.name != "") && (eventState.value.date != "")
                && ((eventState.value.time < eventState.value.endTime) || (eventState.value.endTime == ""))
    }



}