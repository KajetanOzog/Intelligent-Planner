package com.example.io_project.ui.navigation

import com.example.io_project.Constants.ADD_ACTIVITY_DIALOG
import com.example.io_project.Constants.ADD_EVENT_TO_GROUP_DIALOG
import com.example.io_project.Constants.ADD_FRIEND_DIALOG
import com.example.io_project.Constants.ADD_GROUP_DIALOG
import com.example.io_project.Constants.ADD_USER_TO_GROUP_DIALOG
import com.example.io_project.Constants.ARCHIVE_SCREEN
import com.example.io_project.Constants.AUTH_SCREEN
import com.example.io_project.Constants.CALENDAR_SCREEN
import com.example.io_project.Constants.EVENT_DETAILS_DIALOG
import com.example.io_project.Constants.GOALS_SCREEN
import com.example.io_project.Constants.GROUP_SCREEN
import com.example.io_project.Constants.HOME_SCREEN
import com.example.io_project.Constants.PROFILE_SCREEN
import com.example.io_project.Constants.SOCIAL_SCREEN
import com.example.io_project.Constants.SPLASH_SCREEN
import com.example.io_project.Constants.STATS_SCREEN
import com.example.io_project.Constants.TASKS_SCREEN

sealed class Screen(val route: String) {
    object HomeScreen: Screen(HOME_SCREEN)
    object CalendarScreen: Screen(CALENDAR_SCREEN)
    object ProfileScreen: Screen(PROFILE_SCREEN)
    object AuthScreen: Screen(AUTH_SCREEN)
    object ArchiveScreen: Screen(ARCHIVE_SCREEN)
    object GroupScreen: Screen(GROUP_SCREEN)
    object GoalsScreen: Screen(GOALS_SCREEN)
    object SocialScreen: Screen(SOCIAL_SCREEN)
    object SplashScreen: Screen(SPLASH_SCREEN)
    object StatsScreen: Screen(STATS_SCREEN)
    object TasksScreen: Screen(TASKS_SCREEN)
    object AddActivityDialog: Screen(ADD_ACTIVITY_DIALOG)
    object AddGroupDialog: Screen(ADD_GROUP_DIALOG)
    object AddFriendDialog: Screen(ADD_FRIEND_DIALOG)
    object AddEventToGroupDialog: Screen(ADD_EVENT_TO_GROUP_DIALOG)
    object AddUserToGroupDialog: Screen(ADD_USER_TO_GROUP_DIALOG)
    object EventDetailsDialog: Screen(EVENT_DETAILS_DIALOG)


}