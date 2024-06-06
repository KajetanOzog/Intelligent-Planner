package com.example.io_project.ui.screens.goalsscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.dataclasses.Goal
import com.example.io_project.datamanagement.fetchUncompletedGoals
import com.example.io_project.datamanagement.updateGoals
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(

) : ViewModel() {
    var goals = mutableListOf<Goal>()
    var size: Int = 0

    init {
        getGoalsList()
    }


    fun acceptChanges(goalsState: List<Goal>) {
        Firebase.auth.currentUser?.let {
            viewModelScope.launch {
                updateGoals(goalsState, it.uid)
            }
        }
    }

    fun getGoalsList(){
        runBlocking{
            Firebase.auth.currentUser?.let {
                goals = fetchUncompletedGoals(it.uid)?.toMutableList() ?: emptyList<Goal>().toMutableList()
                size = goals.size
            }
        }
    }



}
