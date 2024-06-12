package com.example.io_project.ui.screens.calendarscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.io_project.ui.theme.IO_ProjectTheme
import com.example.io_project.Constants.CALENDAR_SCREEN
import com.example.io_project.Constants.GOALS_SCREEN
import com.example.io_project.Constants.TASKS_SCREEN
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.CalendarTile
import com.example.io_project.ui.components.TopBar
import com.example.io_project.R
import com.example.io_project.dataclasses.Event
import com.example.io_project.ui.components.AddButton
import com.example.io_project.ui.components.SmallTile
import com.example.io_project.ui.components.formatDate
import java.time.format.DateTimeFormatter
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateTo: (String) -> Unit
) {

    val datePickerState: DatePickerState = rememberDatePickerState()
    var datePickerVisible by remember {
        mutableStateOf(false)
    }
    val calendarViewModel: CalendarViewModel = hiltViewModel()
    val dateState = calendarViewModel.dateState.collectAsState()
        .value.format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy", Locale.ENGLISH))
    var eventsState: List<Event> by remember {
        mutableStateOf(calendarViewModel.eventsListState)
    }
    val pickedDate = datePickerState.selectedDateMillis?.let {
        formatDate(it)
    } ?: ""



    Scaffold(
        topBar = {
            TopBar(
                text = "Calendar",
                navigateBack = navigateBack,
                canNavigateBack = true,
                refreshAction = {
                    calendarViewModel.refreshData()
                    eventsState = calendarViewModel.eventsListState
                },
                showRefresh = true,
                showVisibility = true,
                otherEvents = {
                    calendarViewModel.allEvents = !calendarViewModel.allEvents
                    calendarViewModel.refreshData()
                    eventsState = calendarViewModel.eventsListState
                }
            )
        },
        bottomBar = {
            BottomBar(
                navigateTo = navigateTo,
                currentScreenName = CALENDAR_SCREEN
            )
        },
        floatingActionButton = {
            AddButton(navigateTo = navigateTo)
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
            ) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = "Poprzedni dzień",
                    modifier = modifier
                        .clickable {
                            calendarViewModel.getPreviousDay()
                            eventsState = calendarViewModel.eventsListState
                        }
                )
                Text(
                    text = dateState,
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.clickable {
                        datePickerVisible = !datePickerVisible
                    }
                )
                Icon(
                    Icons.Rounded.ArrowForward,
                    contentDescription = "Następny dzień",
                    modifier = modifier
                        .clickable {
                            calendarViewModel.getNextDay()
                            eventsState = calendarViewModel.eventsListState
                        }
                )
            }
            CalendarTile(
                events = eventsState,
                navigateTo = navigateTo,
                modifier = modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
                    .aspectRatio(1f)
            )
            Row() {
                SmallTile(
                    text = "Nawyki",
                    modifier = modifier
                        .padding(end = dimensionResource(id = R.dimen.padding_small))
                        .weight(1f)
                        .clickable { navigateTo(TASKS_SCREEN) }
                )
                SmallTile(
                    text = "Cele długoterminowe",
                    modifier = modifier
                        .padding(start = dimensionResource(id = R.dimen.padding_small))
                        .weight(1f)
                        .clickable { navigateTo(GOALS_SCREEN) }
                )
            }
        }
    }
    if (datePickerVisible) {
        DatePickerDialog(
            onDismissRequest = {
                datePickerVisible = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        calendarViewModel.changeDate(pickedDate)
                        eventsState = calendarViewModel.eventsListState
                        datePickerVisible = false
                    }
                ) {
                    Text("Wybierz")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        datePickerVisible = false
                    }
                ) {
                    Text("Anuluj")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    IO_ProjectTheme {
        CalendarScreen(navigateTo = {}, navigateBack = {})
    }
}