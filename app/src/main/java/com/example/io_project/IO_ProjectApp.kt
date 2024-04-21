package com.example.io_project

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.io_project.ui.screens.ui.calendarscreen.CalendarScreen
import com.example.io_project.ui.screens.ui.homescreen.HomeScreen
import com.example.io_project.ui.screens.ui.profilescreen.ProfileScreen

@Composable
fun IO_ProjectApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable("home_screen") {
            HomeScreen(navigateTo = { navController.navigate(it) })
        }
        composable("calendar_screen") {
            CalendarScreen(navigateTo = { navController.navigate(it) })
        }
        composable("profile_screen") {
            ProfileScreen(navigateTo = { navController.navigate(it) })
        }

    }
}
