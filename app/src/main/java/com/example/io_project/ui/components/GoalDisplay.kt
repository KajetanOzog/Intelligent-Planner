package com.example.io_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.IO_ProjectTheme
import com.example.io_project.R
import com.example.io_project.dataclasses.Goal

@Composable
fun GoalDisplay() {
    val goal = Goal("temp", "18.05.2023")
    GoalDisplay(goal)
}



@Composable
fun GoalDisplay(
    goal: Goal
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .fillMaxWidth()
    ) {
        Text(
            text = goal.name
        )
        Text(
            text = "Data zakończenia: ${goal.deadline}"
        )
        Row {
            CheckboxRow(onValueChange = {}, label = "Wykonano")
        }
    }
}


fun markDone(goal: Goal) {
}

@Preview (showBackground = true)
@Composable
fun GoalDisplayPreview() {
    IO_ProjectTheme {
        GoalDisplay()
    }
}