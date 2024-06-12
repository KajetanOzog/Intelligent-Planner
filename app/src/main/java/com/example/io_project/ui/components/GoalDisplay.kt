package com.example.io_project.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.io_project.R
import com.example.io_project.dataclasses.Goal
import com.example.io_project.ui.theme.IO_ProjectTheme

@Composable
fun GoalDisplay() {
    val goal = Goal("temp", "18.05.2023")
    GoalDisplay(goal)
}


@Composable
fun GoalDisplay(
    goal: Goal,
    canChangeValue: Boolean = true
) {
    var markedDone: Boolean by remember {
        mutableStateOf(goal.done)
    }
    val backgroundColor =
        if (markedDone)
            colorResource(id = R.color.goal_deletion_indicator)
        else
            MaterialTheme.colorScheme.inverseOnSurface

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        Text(
            text = goal.name,
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "Data zakończenia: ${goal.deadline}"
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    if (canChangeValue) {
                        markedDone = !markedDone
                        goal.done = !goal.done
                        Log.d("GoalsVM", "$goal")
                    }
                }
        ) {
            Checkbox(
                checked = markedDone,
                onCheckedChange = null
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
            Text(text = "Wykonano")
        }
    }
}


fun markDone(goal: Goal) {
}

@Preview(showBackground = true)
@Composable
fun GoalDisplayPreview() {
    IO_ProjectTheme {
        GoalDisplay()
    }
}