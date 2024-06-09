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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.io_project.ui.theme.IO_ProjectTheme
import com.example.io_project.R
import com.example.io_project.dataclasses.Task


@Composable
fun TaskDisplay() {
    val task = Task(name = "temp")
    TaskDisplay(task = task, startValue = true)
}


@Composable
fun TaskDisplay(
    task: Task,
    startValue: Boolean
) {
    var markedDone: Boolean by remember {
        mutableStateOf(startValue)
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        Text(
            text = task.name,
            style = MaterialTheme.typography.displayMedium
        )

        Text(
            text = "Ile razy wykonano: ${task.doneCount}",
            style = MaterialTheme.typography.labelSmall
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    markedDone = !markedDone
                    task.completed = !task.completed
                    Log.d("TaskDisplay", task.toString())
                }
        ) {
            Checkbox(
                checked = markedDone,
                onCheckedChange = null
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
            Text(
                text = "Wykonano",
                style = MaterialTheme.typography.labelSmall
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TaskDisplayPreview() {
    IO_ProjectTheme {
        TaskDisplay()
    }
}
