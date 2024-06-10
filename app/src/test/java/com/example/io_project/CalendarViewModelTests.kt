package com.example.io_project

import com.example.io_project.ui.screens.calendarscreen.CalendarViewModel
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CalendarViewModelTests
{
    private val calendarViewModel = CalendarViewModel()

    @Test
    fun getDateString_returnsCorrectDate()
    {
        val dateString = calendarViewModel.getDateString()

        val expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy"))
        Assert.assertEquals(expectedDate, dateString)
    }

    @Test
    fun changeDate_changesDate()
    {
        val dateString = LocalDate.now().minusDays(5)
        val expectedDate = dateString.format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy"))
        calendarViewModel.changeDate(expectedDate)
        Assert.assertEquals(expectedDate, calendarViewModel.getDateString())
    }

    @Test
    fun getPreviousDay_changesDate()
    {
        val dateString = LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy"))
        calendarViewModel.changeDate(dateString)
        calendarViewModel.getPreviousDay()
        val expectedDate = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy"))
        Assert.assertEquals(expectedDate, calendarViewModel.getDateString())
    }

    @Test
    fun getNextDay_changesDate()
    {
        val dateString = LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy"))
        calendarViewModel.changeDate(dateString)
        calendarViewModel.getNextDay()
        val expectedDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy"))
        Assert.assertEquals(expectedDate, calendarViewModel.getDateString())
    }
}