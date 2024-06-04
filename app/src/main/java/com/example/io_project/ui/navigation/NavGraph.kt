package com.example.io_project.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.example.io_project.ui.dialogs.AddActivityDialog
import com.example.io_project.ui.dialogs.AddFriendDialog
import com.example.io_project.ui.dialogs.AddGroupDialog
import com.example.io_project.ui.dialogs.AddUserToGroupDialog
import com.example.io_project.ui.dialogs.AddEventToGroupDialog
import com.example.io_project.ui.dialogs.EventDetailsDialog
import com.example.io_project.ui.screens.archivescreen.ArchiveScreen
import com.example.io_project.ui.screens.profilescreen.ProfileScreen
import com.example.io_project.ui.screens.authscreen.AuthScreen
import com.example.io_project.ui.screens.calendarscreen.CalendarScreen
import com.example.io_project.ui.screens.goalsscreen.GoalsScreen
import com.example.io_project.ui.screens.groupscreen.GroupScreen
import com.example.io_project.ui.screens.homescreen.HomeScreen
import com.example.io_project.ui.screens.socialscreen.SocialScreen
import com.example.io_project.ui.screens.splashscreeen.SplashScreen
import com.example.io_project.ui.screens.statsscreen.StatsScreen
import com.example.io_project.ui.screens.taskscreen.TasksScreen
import com.example.io_project.ui.theme.backgroundDark

@Composable
@ExperimentalAnimationApi
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {

        composable(
            route = "${Screen.HomeScreen.route}/{showSummary}",
            arguments = listOf(
                navArgument("showSummary") {
                    type = NavType.BoolType
                }
            )
        ) {backStackEntry ->
            HomeScreen(
                navigateTo = { navController.navigate(it) },
                showSummary = backStackEntry.arguments?.getBoolean("showSummary") ?: false,
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(route = Screen.CalendarScreen.route) {
            CalendarScreen(navigateTo = { navController.navigate(it) },
                navigateBack = { navController.popBackStack() })
        }
        composable(route = Screen.ArchiveScreen.route) {
            ArchiveScreen(navigateTo = { navController.navigate(it) },
                navigateBack = { navController.popBackStack() })
        }
        composable(route = Screen.GoalsScreen.route) {
            GoalsScreen(navigateTo = { navController.navigate(it) },
                navigateBack = { navController.popBackStack() })
        }
        composable(
            route = "${Screen.GroupScreen.route}/{groupJSON}",
            arguments = listOf(
                navArgument(name = "groupJSON") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            GroupScreen(
                navigateTo = { navController.navigate(it) },
                groupJSON = backStackEntry.arguments?.getString("groupJSON") ?: "",
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(route = Screen.TasksScreen.route) {
            TasksScreen(navigateTo = { navController.navigate(it) },
                navigateBack = { navController.popBackStack() })
        }
        composable(route = Screen.SocialScreen.route) {
            SocialScreen(navigateTo = { navController.navigate(it) },
                navigateBack = { navController.popBackStack() })
        }
        composable(route = Screen.StatsScreen.route) {
            StatsScreen(navigateTo = { navController.navigate(it) },
                navigateBack = { navController.popBackStack() })
        }
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navigateTo = { navController.navigate(it) })
        }
        composable(route = Screen.AuthScreen.route) {
            AuthScreen(navigateTo = { navController.navigate(it) })
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navigateTo = { navController.navigate(it) },
                navigateBack = { navController.popBackStack() })
        }
        dialog(route = Screen.AddActivityDialog.route) {
            AddActivityDialog(navigateBack = { navController.popBackStack() })
        }
        dialog(route = Screen.AddGroupDialog.route) {
            AddGroupDialog(navigateBack = { navController.popBackStack() })
        }
        dialog(route = Screen.AddFriendDialog.route) {
            AddFriendDialog(navigateBack = { navController.popBackStack() })
        }
        dialog(
            route = "${Screen.AddEventToGroupDialog.route}/{groupID}",
            arguments = listOf(
                navArgument(name = "groupID") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            AddEventToGroupDialog(
                groupID = backStackEntry.arguments?.getString("groupID") ?: "",
                navigateBack = { navController.popBackStack() }
            )
        }
        dialog(
            route = "${Screen.AddUserToGroupDialog.route}/{groupID}",
            arguments = listOf(
                navArgument(name = "groupID") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            AddUserToGroupDialog(
                groupID = backStackEntry.arguments?.getString("groupID") ?: "",
                navigateBack = { navController.popBackStack() }
            )
        }
        dialog(
            route = "${Screen.EventDetailsDialog.route}/{eventJSON}",
            arguments = listOf(
                navArgument(name = "eventJSON") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            EventDetailsDialog(
                eventJSON = backStackEntry.arguments?.getString("eventJSON") ?: "",
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}

