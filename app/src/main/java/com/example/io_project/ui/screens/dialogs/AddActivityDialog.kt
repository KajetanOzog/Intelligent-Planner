package com.example.io_project.ui.screens.dialogs

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose.IO_ProjectTheme
import com.example.io_project.R
import com.example.io_project.ui.components.DialogPicker
import kotlinx.coroutines.launch


@Composable
fun AddActivityDialog(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val addEventViewModel: AddEventViewModel = hiltViewModel()
    val addTaskViewModel: AddTaskViewModel = hiltViewModel()
    val addGoalViewModel: AddGoalViewModel = hiltViewModel()

    Dialog(
        onDismissRequest = {
            navigateBack()
        }
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(16.dp)
        ) {
            var currentDialog: String by remember { mutableStateOf("Event") }

            Text(
                text = "Nowa aktywność",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {

                DialogPicker(
                    label = "Wydarzenie",
                    isCurrent = (currentDialog == "Event"),
                    onClick = { currentDialog = "Event" },
                    modifier = modifier.weight(1f)
                )
                DialogPicker(
                    label = "Zadanie",
                    isCurrent = (currentDialog == "Task"),
                    onClick = { currentDialog = "Task" },
                    modifier = modifier.weight(1f)
                )
                DialogPicker(
                    label = "Cel długo...",
                    isCurrent = (currentDialog == "Goal"),
                    onClick = { currentDialog = "Goal" },
                    modifier = modifier.weight(1f)
                )
            }

            Spacer(modifier = modifier.padding(4.dp))

            when (currentDialog){
                "Event" -> AddEventComponent(addEventViewModel = addEventViewModel)
                "Task" -> AddTaskComponent(addTaskViewModel = addTaskViewModel)
                "Goal" -> AddGoalComponent(addGoalViewModel = addGoalViewModel)
            }


            Spacer(modifier = modifier.padding(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            when(currentDialog) {
                                "Event" -> {
                                    addEventViewModel.addEvent()
                                    Log.d("EVENT", "New Event was added")
                                }
                                "Goal" -> {
                                    addGoalViewModel.addGoal()
                                    Log.d("GOAL", "New Goal was added")
                                }
                                "Task" -> {
                                    addTaskViewModel.addTask()
                                    Log.d("TASK", "New Task was added")
                                }
                            }
                            navigateBack()
                        }

                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Dodaj",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Button(
                    onClick = {
                        navigateBack()
                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Anuluj",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddActivityDialogPreview() {
    IO_ProjectTheme {
        AddActivityDialog(navigateBack = {})
    }
}