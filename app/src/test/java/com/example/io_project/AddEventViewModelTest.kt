package com.example.io_project

import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.EventPriority
import com.example.io_project.ui.dialogs.AddEventViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class AddEventViewModelTest {


    private val viewModel = AddEventViewModel()

    @Test
    fun testChangeName() = run {
        val newName = "New Event Name"
        viewModel.changeName(newName)
        assertEquals(newName, viewModel.getEvent().name)
    }

    @Test
    fun testChangeCategory() = run {
        val newCategory = "Praca"
        viewModel.changeCategory(newCategory)
        assertEquals(newCategory, viewModel.getEvent().category)
    }

    @Test
    fun testChangePriority() = run {
        val newPriority = "Wysoki"
        viewModel.changePriority(newPriority)
        assertEquals(EventPriority.HIGH, viewModel.getEvent().priority)
    }

    @Test
    fun testChangeColor() = run {
        val newColor = "#FF5733"
        viewModel.changeColor(newColor)
        assertEquals(newColor, viewModel.getEvent().color)
    }

    @Test
    fun testChangePlace() = run {
        val newPlace = "New Event Place"
        viewModel.changePlace(newPlace)
        assertEquals(newPlace, viewModel.getEvent().place)
    }

    @Test
    fun testChangeTime() = run {
        val newTime = "12:00"
        viewModel.changeTime(newTime)
        assertEquals(newTime, viewModel.getEvent().time)
    }

    @Test
    fun testChangeEndTime() = run {
        val newEndTime = "14:00"
        viewModel.changeEndTime(newEndTime)
        assertEquals(newEndTime, viewModel.getEvent().endTime)
    }

    @Test
    fun testChangeDate() = run {
        val newDate = "Mon, Jun 10 2024"
        viewModel.changeDate(newDate)
        assertEquals(newDate, viewModel.getEvent().date)
    }

    @Test
    fun testChangeEndDate() = run {
        val newEndDate = "Tue, Jun 11 2024"
        viewModel.changeEndDate(newEndDate)
        assertEquals(newEndDate, viewModel.getEvent().endDate)
    }

    @Test
    fun testChangeWeekly() = run {
        val newWeekly = true
        viewModel.changeWeekly(newWeekly)
        assertEquals(newWeekly, viewModel.getEvent().weekly)
    }

    @Test
    fun testChangeReminder() = run {
        val newReminder = true
        viewModel.changeReminder(newReminder)
        assertEquals(newReminder, viewModel.getEvent().reminder)
    }

    @Test
    fun testChangeAlarm() = run {
        val newAlarm = true
        viewModel.changeAlarm(newAlarm)
        assertEquals(newAlarm, viewModel.getEvent().alarm)
    }

    @Test
    fun testChangeReminderTime() = run {
        val newReminderTime = "11:30"
        viewModel.changeReminderTime(newReminderTime)
        assertEquals(newReminderTime, viewModel.getEvent().reminderTime)
    }

    @Test
    fun testChangeVisible() = run {
        val newVisible = true
        viewModel.changeVisible(newVisible)
        assertEquals(newVisible, viewModel.getEvent().visible)
    }

    @Test
    fun testChangeDescription() = run {
        val newDescription = "New Event Description"
        viewModel.changeDescription(newDescription)
        assertEquals(newDescription, viewModel.getEvent().description)
    }

    @Test
    fun testNecessaryArgumentsProvided() = run {
        val event = Event(
            name = "Event",
            date = "Mon, Jun 10 2024",
            reminder = true,
            reminderTime = "10:00",
            alarm = true,
            time = "10:00",
            endTime = "11:00",
            priority = EventPriority.MEDIUM
        )
        viewModel.eventState.value = event
        assertEquals(true, viewModel.necessaryArgumentsProvided())
        val event2 = Event(
            name = "",
            date = "Mon, Jun 10 2024",
            reminder = true,
            reminderTime = "10:00",
            alarm = true,
            time = "10:00",
            endTime = "11:00",
            priority = EventPriority.MEDIUM
        )
        viewModel.eventState.value = event2
        assertEquals(false, viewModel.necessaryArgumentsProvided())
        val event3 = Event(
            name = "Event",
            date = "Mon, Jun 10 2024",
            reminder = true,
            reminderTime = "",
            alarm = true,
            time = "10:00",
            endTime = "11:00",
            priority = EventPriority.MEDIUM
        )
        viewModel.eventState.value = event3
        assertEquals(false, viewModel.necessaryArgumentsProvided())
        val event4 = Event(
            name = "Event",
            date = "",
            reminder = true,
            reminderTime = "10:00",
            alarm = true,
            time = "10:00",
            endTime = "11:00",
            priority = EventPriority.MEDIUM
        )
        viewModel.eventState.value = event4
        assertEquals(false, viewModel.necessaryArgumentsProvided())
    }
}
