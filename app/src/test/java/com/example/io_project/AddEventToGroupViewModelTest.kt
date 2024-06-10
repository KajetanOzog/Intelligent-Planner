package com.example.io_project

import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.EventPriority
import com.example.io_project.ui.dialogs.AddEventToGroupViewModel
import org.junit.Assert
import org.junit.Test

class AddEventToGroupViewModelTest {
    val viewModel = AddEventToGroupViewModel()
    @Test
    fun testChangeName() = run {
        val newName = "New Event Name"
        viewModel.changeName(newName)
        Assert.assertEquals(newName, viewModel.getEvent().name)
    }

    @Test
    fun testChangeCategory() = run {
        val newCategory = "Praca"
        viewModel.changeCategory(newCategory)
        Assert.assertEquals(newCategory, viewModel.getEvent().category)
    }

    @Test
    fun testChangePriority() = run {
        val newPriority = "Wysoki"
        viewModel.changePriority(newPriority)
        Assert.assertEquals(EventPriority.HIGH, viewModel.getEvent().priority)
    }



    @Test
    fun testChangePlace() = run {
        val newPlace = "New Event Place"
        viewModel.changePlace(newPlace)
        Assert.assertEquals(newPlace, viewModel.getEvent().place)
    }

    @Test
    fun testChangeTime() = run {
        val newTime = "12:00"
        viewModel.changeTime(newTime)
        Assert.assertEquals(newTime, viewModel.getEvent().time)
    }

    @Test
    fun testChangeEndTime() = run {
        val newEndTime = "14:00"
        viewModel.changeEndTime(newEndTime)
        Assert.assertEquals(newEndTime, viewModel.getEvent().endTime)
    }

    @Test
    fun testChangeDate() = run {
        val newDate = "Mon, Jun 10 2024"
        viewModel.changeDate(newDate)
        Assert.assertEquals(newDate, viewModel.getEvent().date)
    }

    @Test
    fun testChangeEndDate() = run {
        val newEndDate = "Tue, Jun 11 2024"
        viewModel.changeEndDate(newEndDate)
        Assert.assertEquals(newEndDate, viewModel.getEvent().endDate)
    }

    @Test
    fun testChangeDescription() = run {
        val newDescription = "New Event Description"
        viewModel.changeDescription(newDescription)
        Assert.assertEquals(newDescription, viewModel.getEvent().description)
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
        val event2 = Event(
            name = "",
            date = "Mon, Jun 10 2024",
            alarm = true,
            time = "10:00",
            endTime = "11:00",
            priority = EventPriority.MEDIUM
        )
        val event3 = Event(
            name = "Event",
            date = "",
            alarm = true,
            time = "10:00",
            endTime = "11:00",
            priority = EventPriority.MEDIUM
        )
        val event4 = Event(
        name = "",
        date = "Mon, Jun 10 2024",
        alarm = true,
        time = "10:00",
        endTime = "11:00",
        priority = EventPriority.MEDIUM
    )
        viewModel.eventState.value = event
        Assert.assertEquals(true, viewModel.necessaryArgumentsProvided())
        viewModel.eventState.value = event2
        Assert.assertEquals(false, viewModel.necessaryArgumentsProvided())
        viewModel.eventState.value = event3
        Assert.assertEquals(false, viewModel.necessaryArgumentsProvided())
        viewModel.eventState.value = event4
        Assert.assertEquals(false, viewModel.necessaryArgumentsProvided())
    }
}