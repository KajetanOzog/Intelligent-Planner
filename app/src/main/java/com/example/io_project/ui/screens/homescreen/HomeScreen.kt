package com.example.io_project.ui.screens.homescreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.IO_ProjectTheme
import com.example.io_project.Constants.CALENDAR_SCREEN
import com.example.io_project.Constants.GOALS_SCREEN
import com.example.io_project.Constants.SOCIAL_SCREEN
import com.example.io_project.Constants.TASKS_SCREEN
import com.example.io_project.R
import com.example.io_project.dataclasses.Event
import com.example.io_project.ui.components.AddButton
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.CalendarTile
import com.example.io_project.ui.components.GreetingTile
import com.example.io_project.ui.components.SmallTile
import com.example.io_project.ui.components.TopBar
import com.example.io_project.user.Settings

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateTo: (route: String) -> Unit,
    showSummary: Boolean
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    var eventsState: List<Event> by remember {
        mutableStateOf(homeViewModel.todaysEvents)
    }

    Scaffold(
        topBar = {
            TopBar(
                text = stringResource(id = R.string.app_name),
                navigateBack = navigateBack,
                refreshAction = {
                    homeViewModel.refreshData()
                    eventsState = homeViewModel.todaysEvents
                },
                canNavigateBack = false,
                showRefresh = true
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
            GreetingTile(
                defaultSummaryVisibility = showSummary,
                events = eventsState
            )
            Row {
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
                Text(
                    text = "Kalendarz",
                    style = MaterialTheme.typography.displayMedium
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))

            CalendarTile(
                events = eventsState,
                navigateTo = navigateTo,
                modifier = modifier
                    .clickable(onClick = { navigateTo(CALENDAR_SCREEN) })
                    .aspectRatio(1.2f)
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))

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
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
                    Spacer(modifier = modifier.weight(1f))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    IO_ProjectTheme {
        HomeScreen(navigateTo = {}, navigateBack = {}, showSummary = false)
    }
}