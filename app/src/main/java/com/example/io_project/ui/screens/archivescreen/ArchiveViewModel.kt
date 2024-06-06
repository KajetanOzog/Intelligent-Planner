package com.example.io_project.ui.screens.archivescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.io_project.dataclasses.Goal
import com.example.io_project.datamanagement.fetchCompletedGoals
import com.example.io_project.datamanagement.getTasks
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor(

): ViewModel() {
    var goals: List<Goal> = emptyList()
    var size: Int = 0
    init{
        getTasksToList()
    }

    fun getTasksToList() {
        runBlocking {
            Firebase.auth.currentUser?.let {
                goals = fetchCompletedGoals(it.uid) ?: emptyList()
                size = goals.size
            }
        }
    }
}