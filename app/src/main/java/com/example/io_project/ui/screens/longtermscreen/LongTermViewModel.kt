package com.example.io_project.ui.screens.longtermscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.dataclasses.Goal
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LongTermViewModel @Inject constructor(

): ViewModel() {
    val db = FirebaseFirestore.getInstance()
    var goals: List<Goal> = getGoalsList()



    fun getGoalsList(): List<Goal> {
        val returnList: List<Goal> = emptyList()

        viewModelScope.launch(
            Firebase.auth.currentUser?.let {
                val docPath = db.collection("users").document(it.uid)
                    .collection("goals").document("unfinished")
                returnList =
            }
        )


        return returnList
    }

}
