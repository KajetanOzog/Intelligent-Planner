package com.example.io_project.ui.screens.homescreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.IO_ProjectTheme
import com.example.io_project.Constants.CALENDAR_SCREEN
import com.example.io_project.Constants.GOALS_SCREEN
import com.example.io_project.Constants.SOCIAL_SCREEN
import com.example.io_project.Constants.TASKS_SCREEN
import com.example.io_project.R
import com.example.io_project.ui.components.AddButton
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.CalendarTile
import com.example.io_project.ui.components.GreetingTile
import com.example.io_project.ui.components.SmallTile
import com.example.io_project.ui.components.TopBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateTo: (route: String) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                text = stringResource(id = R.string.app_name),
                navigateBack = navigateBack,
                canNavigateBack = false
            )
        },

        bottomBar = {
            BottomBar(
                navigateTo = navigateTo,
                currentScreenName = "home_screen"
            )
        },

        floatingActionButton = {
            AddButton(navigateTo = navigateTo)
        },
        modifier = modifier
            .fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(ScrollState(0))
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            GreetingTile()

            CalendarTile(
                modifier = modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
                    .clickable(onClick = { navigateTo(CALENDAR_SCREEN) })
                    .aspectRatio(1.2f)
            )

            Column() {
                Row() {
                    SmallTile(
                        text = "Nawyki",
                        modifier = modifier
                            .padding(end = dimensionResource(id = R.dimen.padding_small))
                            .weight(1f)
                            .clickable { navigateTo(TASKS_SCREEN) }
                    )
                    SmallTile(
                        text = "Społeczność",
                        modifier = modifier
                            .padding(start = dimensionResource(id = R.dimen.padding_small))
                            .weight(1f)
                            .clickable { navigateTo(SOCIAL_SCREEN) }
                    )
                }
                Row() {
                    SmallTile(
                        text = "Cele długoterminowe",
                        modifier = modifier
                            .weight(1f)
                            .clickable { navigateTo(GOALS_SCREEN) }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    IO_ProjectTheme {
        HomeScreen(navigateTo = {}, navigateBack = {})
    }
}