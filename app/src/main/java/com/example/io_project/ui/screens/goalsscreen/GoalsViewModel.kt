package com.example.io_project.ui.screens.goalsscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.dataclasses.Goal
import com.example.io_project.datamanagement.fetchUncompletedGoals
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(

) : ViewModel() {
    var goals: List<Goal> = emptyList()
    init {
        getGoalsList()
        Log.d("GoalsScreensFetch", "${goals.size}")
    }


    fun getGoalsList() {
        viewModelScope.launch {
            Firebase.auth.currentUser?.let {
                goals = fetchUncompletedGoals(it.uid) ?: emptyList()
            }
        }
    }

    private fun getGoalsList2() {
        goals = listOf(Goal("1", "12.12.2012"), Goal("2", "12.12.2012"))
    }

}
