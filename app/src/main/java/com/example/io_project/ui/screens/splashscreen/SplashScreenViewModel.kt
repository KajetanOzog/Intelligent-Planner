package com.example.io_project.ui.screens.splashscreen

import androidx.lifecycle.ViewModel
import com.example.io_project.model.service.AccountService
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {

    fun onAppStart(navigateTo: (String) -> Unit) {
        if (accountService.hasUser()) navigateTo("home_screen")
        else navigateTo("sign_in_screen")
    }
}