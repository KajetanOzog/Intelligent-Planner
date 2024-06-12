package com.example.io_project.ui.screens.groupscreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
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
import com.example.io_project.Constants.GROUP_SCREEN
import com.example.io_project.R
import com.example.io_project.dataclasses.Event
import com.example.io_project.ui.components.AddToGroupButton
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.CalendarTile
import com.example.io_project.ui.components.TopBar
import com.example.io_project.ui.components.UsersColumn
import com.example.io_project.ui.components.formatDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupScreen(
    modifier: Modifier = Modifier,
    groupJSON: String,
    navigateTo: (String) -> Unit,
    navigateBack: () -> Unit
) {
    val datePickerState: DatePickerState = rememberDatePickerState()
    var datePickerVisible by remember {
        mutableStateOf(false)
    }
    val groupViewModel =
        hiltViewModel<GroupViewModel, GroupViewModel.GroupViewModelFactory> { factory ->
            factory.create(groupJSON)
        }
    val dateState = groupViewModel.dateState.collectAsState()
        .value.format(DateTimeFormatter.ofPattern("EEE, MMM d yyyy", Locale.ENGLISH))

    var eventsState: List<Event> by remember {
        mutableStateOf(groupViewModel.eventsListState)
    }
    val pickedDate = datePickerState.selectedDateMillis?.let {
        formatDate(it)
    } ?: ""

    Scaffold(
        topBar = {
            TopBar(
                text = groupViewModel.group.groupName,
                navigateBack = navigateBack,
                canNavigateBack = true,
                refreshAction = {
                    groupViewModel.refreshData()
                    eventsState = groupViewModel.eventsListState
                },
                showRefresh = true
            )
        },
        bottomBar = {
            BottomBar(
                navigateTo = navigateTo,
                currentScreenName = GROUP_SCREEN
            )
        },
        floatingActionButton = {
            if (groupViewModel.currentUserIsAdmin()) {
                AddToGroupButton(
                    groupID = groupViewModel.group.groupID,
                    navigateTo = navigateTo
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .verticalScroll(ScrollState(0))
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
                            groupViewModel.getPreviousDay()
                            eventsState = groupViewModel.eventsListState
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
                    contentDescription = "Poprzedni dzień",
                    modifier = modifier
                        .clickable {
                            groupViewModel.getNextDay()
                            eventsState = groupViewModel.eventsListState
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
            Text(
                text = "Członkowie:",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            UsersColumn(users = groupViewModel.members)

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
                        groupViewModel.changeDate(pickedDate)
                        eventsState = groupViewModel.eventsListState
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
fun GroupScreenPreview() {
    IO_ProjectTheme {
        GroupScreen(navigateTo = {}, navigateBack = {}, groupJSON = "")
    }
}