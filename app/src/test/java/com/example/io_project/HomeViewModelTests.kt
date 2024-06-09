package com.example.io_project

import com.example.io_project.ui.screens.homescreen.HomeViewModel
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeViewModelTests
{
    val homeViewModel = HomeViewModel()
    @Test
    fun getTodaysDateString_returnsCorrectDate()
    {
        val expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy"))
        Assert.assertEquals(expectedDate, homeViewModel.getTodaysDateString())
    }
}