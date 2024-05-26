package com.example.io_project.ui.screens.goalsscreen

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose.IO_ProjectTheme
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.SmallTile
import com.example.io_project.ui.components.TopBar
import com.example.io_project.R
import com.example.io_project.dataclasses.Goal
import com.example.io_project.ui.components.GoalDisplay

@Composable
fun GoalsScreen(
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val goalsViewModel: GoalsViewModel = hiltViewModel()
    Scaffold(
        topBar = {
            TopBar(
                text = "Cele długoterminowe",
                navigateTo = navigateTo,
                canNavigateBack = true
            )
        },
        bottomBar = {
            BottomBar(
                navigateTo = navigateTo,
                currentScreenName = "long_term_screen"
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .verticalScroll(ScrollState(0))
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            if (goalsViewModel.size == 0) {
                NoGoalsText()
            } else {
                GoalsColumn(goals = goalsViewModel.goals)
            }

        }
    }
}

@Composable
fun NoGoalsText() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Brak celów")
    }
}


@Composable
fun GoalsColumn(
    goals: List<Goal>
) {
    Column(
        modifier = Modifier
            .verticalScroll(ScrollState(0))
            .fillMaxSize()
    ) {
        goals.forEach { goal ->
            GoalDisplay(goal)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GoalsScreenPreview() {
    IO_ProjectTheme {
        GoalsScreen(navigateTo = {})
    }
}