package com.example.io_project.ui.screens.archivescreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.example.io_project.ui.screens.goalsscreen.GoalsColumn
import com.example.io_project.ui.screens.goalsscreen.NoGoalsText

@Composable
fun ArchiveScreen(
    navigateTo: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val archiveViewModel: ArchiveViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            TopBar(
                text = "Archiwum",
                navigateBack = navigateBack,
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
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            if (archiveViewModel.size == 0) {
                NoGoalsText()
            } else {
                ArchiveColumn(goals = archiveViewModel.goals)
            }
        }
    }
}

@Composable
fun ArchiveColumn(
    goals: List<Goal>
) {
    Column(
        modifier = Modifier
            .verticalScroll(ScrollState(0))
            .fillMaxSize()
    ) {
        goals.forEach { goal ->
            GoalDisplay(goal, canChangeValue = false)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    IO_ProjectTheme {
        ArchiveScreen(navigateTo = {}, navigateBack = {})
    }
}