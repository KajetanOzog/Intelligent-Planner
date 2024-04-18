package com.example.io_project.ui.screens.ui.calendarscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.TopBar

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    navigateTo: (route: String) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                text = "Calendar",
                navigateTo = navigateTo,
                canNavigateBack = true
            )
        },
        bottomBar = {
            BottomBar()
        }
    ) { paddingValues ->
        Column(
            modifier = modifier.padding(paddingValues)
        ) {
            Text(text = "calendar")
        }
    }
}

