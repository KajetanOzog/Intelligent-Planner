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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.IO_ProjectTheme
import com.example.io_project.Constants.LONG_TERM_SCREEN
import com.example.io_project.Constants.TASKS_SCREEN
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.CalendarTile
import com.example.io_project.ui.components.TopBar
import com.example.io_project.R
import com.example.io_project.ui.components.AddButton
import com.example.io_project.ui.components.SmallTile
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getCurrentDate(currentDate: LocalDate): String{
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    return currentDate.format(formatter)
}

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    navigateTo: (route: String) -> Unit
) {
    var currentData by remember { mutableStateOf(LocalDate.now()) }

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
                        .clickable { currentData = currentData.minusDays(1)}
                )
                Text(
                    text = getCurrentDate(currentData),
                    style = MaterialTheme.typography.displayMedium
                )
                Icon(
                    Icons.Rounded.ArrowForward,
                    contentDescription = "Poprzedni dzień",
                    modifier = modifier
                            .clickable { currentData = currentData.plusDays(1)}
                )
            }
            CalendarTile(
                date = getCurrentDate(currentData),
                modifier = modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
                    .aspectRatio(0.9f)
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
                    text = "Cele długotermi...",
                    modifier = modifier
                        .padding(start = dimensionResource(id = R.dimen.padding_small))
                        .weight(1f)
                        .clickable { navigateTo(LONG_TERM_SCREEN) }
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    IO_ProjectTheme {
        CalendarScreen(navigateTo = {})
    }
}