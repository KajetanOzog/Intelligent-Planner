package com.example.io_project.ui.screens.taskscreen

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.io_project.ui.theme.IO_ProjectTheme
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.TopBar
import com.example.io_project.R
import com.example.io_project.dataclasses.Task
import com.example.io_project.ui.components.AcceptChangesButton
import com.example.io_project.ui.components.TaskDisplay

@Composable
fun TasksScreen(
    navigateTo: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tasksViewModel: TasksViewModel = hiltViewModel()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(
                text = "Nawyki",
                navigateBack = navigateBack,
                canNavigateBack = true
            )
        },
        bottomBar = {
            BottomBar(
                navigateTo = navigateTo,
                currentScreenName = "tasks_screen"
            )
        },
        floatingActionButton = {
            AcceptChangesButton(
                acceptChanges = { tasksViewModel.acceptChanges() },
                context = context
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            val noTasks: Boolean by remember {
                mutableStateOf(tasksViewModel.tasks.isEmpty())
            }

            if (noTasks) {
                NoTasksText()
            } else {
                TasksColumn(tasks = tasksViewModel.tasks)
            }

        }
    }
}

@Composable
fun NoTasksText() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Brak zadań")
    }
}


@Composable
fun TasksColumn(
    tasks: List<Task>
) {
    Column(
        modifier = Modifier
            .verticalScroll(ScrollState(0))
            .fillMaxSize()
    ) {


        tasks.forEach { task ->
            Log.d("TasksDisplay", "$task")
            TaskDisplay(task, task.completed)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    IO_ProjectTheme {
        TasksScreen(navigateTo = {}, navigateBack = {})
    }
}