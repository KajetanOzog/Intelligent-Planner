package com.example.io_project.ui.dialogs

import addEventToFirestore
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.Constants.BUSY_INDICATOR
import com.example.io_project.Constants.DATE_FORMATTER_PATTERN
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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextInt

@HiltViewModel
class AssistantViewModel @Inject constructor(

) : ViewModel() {

    // Klasa przetestowana manualnie

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
        // Lista na ktorej bedziemy zaznaczac zajete godziny przez wydarzenia
        val freeHoursBool = ArrayList<Boolean>()
        repeat(24) {
            freeHoursBool.add(true)
        }
        val todaysDate: String =
            LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMATTER_PATTERN, Locale.ENGLISH))
        val currentHour: Int =
            LocalTime.now().format(DateTimeFormatter.ofPattern("HH")).toInt()
        // Jesli sprawdzamy zajęte godziny na dzisiaj, to wyłączamy te które już upłynęły
        if (date == todaysDate) {
            for (i in 0..<currentHour) {
                freeHoursBool[i] = false
            }
        }
        // Filtrujemy eventy z danego dnia z bazy eventow uzytkownika
        val eventsAtDate = usersEvents.filter { it.date == date }
        for (event in eventsAtDate) {
            // Jezeli event ma tylko godzine poczatkowa, to "zajmujemy" godzinę o której się rozpoczyna
            // oraz kolejną (np. 8:45 -> 8, 9 zajęta, 8:00 -> 8, 9 zajęta)
            // Eventy które rozprzestrzeniaja zaczynają się w poprzednich dniach i nachodzą na ten nie
            // są brane pod uwagę
            if (event.time != ""){
                if (event.endTime == "") {
                    val start = event.time.substringBefore(":").toInt() - 1
                    freeHoursBool[start] = false
                    if (start < 23)
                        freeHoursBool[start + 1] = false
                } else {
                    val start = event.time.substringBefore(":").toInt() - 1
                    val end = event.endTime.substringBefore(":").toInt() - 1
                    for (i in start..end) {
                        freeHoursBool[i] = false
                    }
                }
            }
        }
        // Wyciągamy godziny w których użytkownik nie jest zajęty
        val freeHours = ArrayList<Int>()
        for (i in 7..21) {
            if (freeHoursBool[i])
                freeHours.add(i + 1)
        }
        // A na koniec z takiej listy losujemy dowolną godzinę i formatujemy wg formatu z eventów
        // jezeli brak wolnych godzin uniemozliwiamy dodanie na ten dzien
        return if (freeHours.isEmpty()) BUSY_INDICATOR
            else "${freeHours.random()}:00"
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