package com.example.io_project.ui.screens.splashscreeen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.io_project.Constants.AUTH_SCREEN
import com.example.io_project.Constants.HOME_SCREEN
import com.example.io_project.Constants.SPLASH_DELAY
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateTo: (String) -> Unit
) {
    LaunchedEffect(true) {
        delay(SPLASH_DELAY)
        navigateTo(chooseNavigationDestination())
    }
}


fun chooseNavigationDestination(): String {
    return if(Firebase.auth.currentUser != null) HOME_SCREEN else AUTH_SCREEN
}