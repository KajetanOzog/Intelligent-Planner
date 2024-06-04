package com.example.io_project.ui.screens.splashscreeen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.Constants
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

) : ViewModel() {
    fun chooseNavigationDestination(showSummary: Boolean): String {
        Log.d("LOGIN_STATUS", "Current user -> " + Firebase.auth.currentUser)
        return if (Firebase.auth.currentUser != null) "${Constants.HOME_SCREEN}/${showSummary}" else Constants.AUTH_SCREEN
    }

    fun displaySummary(
        lastVisitDate: String,
        settingAllows: Boolean,
        changeVisitDate: suspend (String) -> Unit
    ): Boolean {
        val todaysDate =
            LocalDate.now().format(DateTimeFormatter.ofPattern(Constants.DATE_FORMATTER_PATTERN))
        return if (settingAllows && lastVisitDate != todaysDate) {
            viewModelScope.launch {
                changeVisitDate(todaysDate)
            }
            true
        } else {
            false
        }
    }
}