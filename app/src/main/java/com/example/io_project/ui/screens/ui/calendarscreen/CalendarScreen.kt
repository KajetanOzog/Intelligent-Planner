package com.example.io_project.ui.screens.ui.calendarscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.CalendarTile
import com.example.io_project.ui.components.TopBar
import com.example.io_project.R

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
            BottomBar(
                navigateTo = navigateTo,
                currentScreenName = "calendar_screen"
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            CalendarTile()
        }
    }
}

