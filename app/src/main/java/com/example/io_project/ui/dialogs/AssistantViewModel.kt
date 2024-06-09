package com.example.io_project.ui.dialogs

import addEventToFirestore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.Constants.DEFAULT_COLOR_HEX
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.EventPriority
import com.example.io_project.dataclasses.EventSuggestion
import com.example.io_project.dataclasses.EventSuggestionsDatabase
import com.example.io_project.datamanagement.fetchAllEvents
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextInt

@HiltViewModel
class AssistantViewModel @Inject constructor(

) : ViewModel() {

    var event: Event = Event(
        priority = EventPriority.MEDIUM,
        color = DEFAULT_COLOR_HEX
    )
    private var usersEvents: List<Event> = emptyList()
    private var eventSuggestionsDB: List<EventSuggestion> = EventSuggestionsDatabase().suggestions
    var suggestionDisplayList: MutableList<EventSuggestion> = mutableListOf(
        EventSuggestion(),
        EventSuggestion(),
        EventSuggestion(),
        EventSuggestion()
    )

    init {
        viewModelScope.launch {
            Firebase.auth.currentUser?.let {
                usersEvents = fetchAllEvents(it.uid) ?: usersEvents
            }
        }
    }

    fun getRandomSuggestions() {
        val indexes: List<Int> = generateSequence { Random.nextInt(0..24) }
            .distinct()
            .take(4)
            .sorted()
            .toList()
        for (i in 0..3) {
            suggestionDisplayList[i] = eventSuggestionsDB[indexes[i]]
        }
    }

    fun getSuggestionsByCategory(category: String) {
        val categorizedSuggestions = eventSuggestionsDB.filter { it.category == category }
        val indexes: List<Int> =
            generateSequence { Random.nextInt(categorizedSuggestions.indices) }
                .distinct()
                .take(4)
                .sorted()
                .toList()
        for (i in 0..3) {
            suggestionDisplayList[i] = categorizedSuggestions[indexes[i]]
        }
    }

    fun chooseSuggestion(suggestion: EventSuggestion) {
        event = event.copy(
            name = suggestion.name,
            category = suggestion.category
        )
    }

    fun changeDate(newDate: String) {
        event = event.copy(date = newDate)
    }

    fun changeTime(newTime: String) {
        event = event.copy(time = newTime)
    }

    fun changeVisible(newVisible: Boolean) {
        event = event.copy(visible = newVisible)
    }

    fun getFreeTime(date: String): String {
        val freeHoursBool = ArrayList<Boolean>()
        repeat(24) {
            freeHoursBool.add(true)
        }
        val eventsAtDate = usersEvents.filter { it.date == date }
        for (event in eventsAtDate) {
            if (event.endTime == "")
                freeHoursBool[event.time.substringBefore(":").toInt() - 1] = false
            else {
                val start = event.time.substringBefore(":").toInt() - 1
                val end = event.endTime.substringBefore(":").toInt() - 1
                for (i in start..end) {
                    freeHoursBool[i] = false
                }
            }
        }
        val freeHours = ArrayList<Int>()
        for (i in 7..21) {
            if (freeHoursBool[i])
                freeHours.add(i + 1)
        }
        return "${freeHours.random()}:00"
    }

    fun submitSuggestedEvent() {
        viewModelScope.launch {
            Firebase.auth.currentUser?.let {
                addEventToFirestore(
                    event = event,
                    userID = it.uid,
                    isRegular = false
                )
            }
        }
    }


}