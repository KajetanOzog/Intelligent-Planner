package com.example.io_project.ui.navigation

import com.example.io_project.Constants.AUTH_SCREEN

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object CalendarScreen: Screen("calendar_screen")
    object ProfileScreen: Screen("profile_screen")
    object SignInScreen: Screen("sign_in_screen")
    object AuthScreen: Screen("auth_screen")

}