package com.example.io_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    var markedDone: Boolean by remember {
        mutableStateOf(goal.done)
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.outlineVariant)
            .padding(dimensionResource(id = R.dimen.padding_small))
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = goal.name
            )
            if (markedDone) {
                Icon(
                    Icons.Rounded.Clear,
                    contentDescription = "Delete goal from display",
                    modifier = Modifier.clickable {
                        /* TODO: Przenoszenie celu do completed i usuwanie go z widoku */
                    }
                )
            }
        }
        Text(
            text = "Data zakończenia: ${goal.deadline}"
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    markedDone = !markedDone
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