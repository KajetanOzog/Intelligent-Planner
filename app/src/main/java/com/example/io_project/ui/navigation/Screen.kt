package com.example.io_project.ui.navigation

import com.example.io_project.Constants.AUTH_SCREEN

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object CalendarScreen: Screen("calendar_screen")
    object ProfileScreen: Screen("profile_screen")
    object AuthScreen: Screen("auth_screen")
    object ArchiveScreen: Screen("archive_screen")
    object GroupScreen: Screen("group_screen")
    object LongTermScreen: Screen("long_term_screen")
    object SocialScreen: Screen("social_screen")
    object SplashScreen: Screen("splash_screen")
    object StatsScreen: Screen("stats_screen")
    object TasksScreen: Screen("tasks_screen")
    object AddEventDialog: Screen("add_event_dialog")

}