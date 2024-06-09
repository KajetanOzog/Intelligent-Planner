package com.example.io_project.ui.dialogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.EventPriority
import com.example.io_project.datamanagement.JSONToEvent
import com.example.io_project.datamanagement.removeEvent
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel(assistedFactory = EventDetailsViewModel.EventDetailsViewModelFactory::class)
class EventDetailsViewModel @AssistedInject constructor(
    @Assisted val eventJSON: String
): ViewModel(){

    @AssistedFactory
    interface EventDetailsViewModelFactory {
        fun create(eventJSON: String): EventDetailsViewModel
    }

    val event: Event = JSONToEvent(eventJSON)
    val priorityMap: Map<EventPriority, String> = mapOf(
        EventPriority.LOW to "NISKI",
        EventPriority.MEDIUM to "ŚREDNI",
        EventPriority.HIGH to "WYSOKI"
    )
    val timeString: String = event.time + if (event.endTime != "") "-" + event.endTime else ""


    fun deleteEvent() {
        viewModelScope.launch {
            Firebase.auth.currentUser?.let {
                removeEvent(event, it.uid)
            }
        }
    }
}