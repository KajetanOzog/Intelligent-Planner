package com.example.io_project

import com.example.io_project.dataclasses.Task
import com.example.io_project.datamanagement.getTasks
import com.example.io_project.datamanagement.updateTasks
import com.example.io_project.ui.screens.taskscreen.TasksViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import javax.inject.Inject


class TasksViewModelTest {



    val mockFirebaseAuth = mockk<FirebaseAuth>()

    @Inject
    private lateinit var viewModel: TasksViewModel

    private val fakeTasks = listOf(Task("Test Task 1"), Task("Test Task 2"))

    @Before
    fun setUp(): Unit = runBlocking {
        MockitoAnnotations.initMocks(this)


        // Initialize the view model
        viewModel = TasksViewModel()

        // Mock getTasks method
        `when`(getTasks("test_uid")).thenReturn(fakeTasks)
    }

    @Test
    fun testInitialTasksLoading() = run { // Ensure tasks are loaded on initialization
        viewModel.getTasksToList()
        assert(viewModel.tasks == fakeTasks)
        assert(viewModel.size == fakeTasks.size)
    }

    @Test
    fun testAcceptChanges(): Unit = runBlocking {
        // Set tasks
        viewModel.tasks = fakeTasks

        // Call acceptChanges
        viewModel.acceptChanges()

        // Verify updateTasks is called with correct arguments
        verify(updateTasks(fakeTasks, "test_uid"))
    }

    @Test
    fun testGetTasksToList_noUser() = run {
        // Simulate no user logged in

        viewModel.getTasksToList()

        // Ensure tasks list is empty
        assert(viewModel.tasks.isEmpty())
        assert(viewModel.size == 0)
    }

    @Test
    fun testAcceptChanges_noUser(): Unit = runBlocking {
        // Simulate no user logged in

        viewModel.acceptChanges()

        // Verify updateTasks is never called
        verify(updateTasks(fakeTasks, "test_uid"), never())
    }
}