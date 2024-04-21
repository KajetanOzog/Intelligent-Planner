package com.example.io_project

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object CalendarScreen: Screen("calendar_screen")

    object ProfileScreen: Screen("profile_screen")
}