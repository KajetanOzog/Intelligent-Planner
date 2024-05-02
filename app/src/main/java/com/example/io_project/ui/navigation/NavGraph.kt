package com.example.io_project.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.io_project.ui.screens.profilescreen.ProfileScreen
import com.example.io_project.ui.screens.authscreen.AuthScreen
import com.example.io_project.ui.screens.calendarscreen.CalendarScreen
import com.example.io_project.ui.screens.homescreen.HomeScreen

@Composable
@ExperimentalAnimationApi
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.AuthScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable("home_screen") {
            HomeScreen(navigateTo = { navController.navigate(it) })
        }
        composable("calendar_screen") {
            CalendarScreen(navigateTo = { navController.navigate(it) })
        }
        composable(
            route = Screen.AuthScreen.route
        ) {
            AuthScreen(
                navigateToProfileScreen = {
                    navController.navigate(Screen.ProfileScreen.route)
                }
            )
        }
        composable(
            route = Screen.ProfileScreen.route
        ) {
            ProfileScreen(
                navigateTo = {
                    navController.navigate(it)
                }
            )
        }
    }
}