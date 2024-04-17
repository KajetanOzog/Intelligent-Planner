package com.example.io_project

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.io_project.ui.screens.ui.calendarscreen.CalendarScreen
import com.example.io_project.ui.screens.ui.homescreen.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.CalendarScreen.route) {
            CalendarScreen(navController = navController)
        }
    }
}
