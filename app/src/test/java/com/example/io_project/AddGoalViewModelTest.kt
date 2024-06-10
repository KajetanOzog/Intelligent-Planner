package com.example.io_project

import com.example.io_project.dataclasses.Goal
import com.example.io_project.ui.dialogs.AddGoalViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class AddGoalViewModelTest {

    val viewModel = AddGoalViewModel()
    @Test
    fun testChangeName() = run {
        val newName = "New Goal Name"
        viewModel.changeName(newName)
        assertEquals(newName, viewModel.getGoal().name)
    }

    @Test
    fun testChangeDeadline() = run {
        val newDeadline = "2024-12-31"
        viewModel.changeDeadline(newDeadline)
        assertEquals(newDeadline, viewModel.getGoal().deadline)
    }

    @Test
    fun testNecessaryArgumentsProvided() = run {
        val goal = Goal(
            name = "Goal",
            deadline = "2024-12-31"
        )
        viewModel.goalState.value = goal
        assertEquals(true, viewModel.necessaryArgumentsProvided())
        val goal2 = Goal(
            name = "",
            deadline = "2024-12-31"
        )
        viewModel.goalState.value = goal2
        assertEquals(false, viewModel.necessaryArgumentsProvided())
        val goal3 = Goal(
            name = "Goal",
            deadline = ""
        )
        viewModel.goalState.value = goal3
        assertEquals(false, viewModel.necessaryArgumentsProvided())
    }
}