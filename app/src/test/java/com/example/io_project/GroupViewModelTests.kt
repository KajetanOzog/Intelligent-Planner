package com.example.io_project

import com.example.io_project.ui.screens.groupscreen.GroupViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Na potrzeby testów w GroupViewModel wykomentowane zostały metody Log.d, updateEvents oraz refreshData,
// aby nie trzeba było mockować Log oraz FiebaseAuth

class GroupViewModelTests
{
    private val groupJSON = """
        {
            "ownerID": "testOwner",
            "groupID": "testGroup",
            "groupMembers": []
        }
    """

    private val groupViewModel = GroupViewModel(groupJSON)

    @Test
    fun getDateString_returnsCorrectDate()
    {
        val dateString = groupViewModel.getDateString()

        val expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy"))
        assertEquals(expectedDate, dateString)
    }

    @Test
    fun changeDate_changesDate()
    {
        val dateString = LocalDate.now().minusDays(5)
        val expectedDate = dateString.format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy"))
        groupViewModel.changeDate(expectedDate)
        assertEquals(expectedDate, groupViewModel.getDateString())
    }

    @Test
    fun getPreviousDay_changesDate()
    {
        val dateString = LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy"))
        groupViewModel.changeDate(dateString)
        groupViewModel.getPreviousDay()
        val expectedDate = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy"))
        assertEquals(expectedDate, groupViewModel.getDateString())
    }

    @Test
    fun getNextDay_changesDate()
    {
        val dateString = LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy"))
        groupViewModel.changeDate(dateString)
        groupViewModel.getNextDay()
        val expectedDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy"))
        assertEquals(expectedDate, groupViewModel.getDateString())
    }
}