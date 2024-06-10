package com.example.io_project

import com.example.io_project.dataclasses.Task
import com.example.io_project.ui.dialogs.AddTaskViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddTaskViewModelTest {
    private val viewModel = AddTaskViewModel()

    @Test
    fun testChangeName() = run {
        val newName = "New Task Name"
        viewModel.changeName(newName)
        assertEquals(newName, viewModel.getTask().name)
    }

    @Test
    fun testNecessaryArgumentsProvided() = run {
        val task = Task(
            name = "Task",
            addedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        )
        viewModel.taskState.value = task
        assertEquals(true, viewModel.necessaryArgumentsProvided())
    }
}