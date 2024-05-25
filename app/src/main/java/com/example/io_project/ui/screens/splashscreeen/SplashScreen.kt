package com.example.io_project.ui.screens.splashscreeen

import android.util.Log
import android.window.SplashScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.IO_ProjectTheme
import com.example.io_project.Constants.AUTH_SCREEN
import com.example.io_project.Constants.HOME_SCREEN
import com.example.io_project.Constants.SPLASH_DELAY
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navigateTo: (String) -> Unit
) {
    LaunchedEffect(true) {
        delay(SPLASH_DELAY)
        navigateTo(chooseNavigationDestination())
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Intelligent planer",
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Bold
        )
    }
}


fun chooseNavigationDestination(): String {
    Log.d("LOGIN_STATUS", "Current user -> " + Firebase.auth.currentUser)
    return if(Firebase.auth.currentUser != null) HOME_SCREEN else AUTH_SCREEN
}

@Preview (showBackground = true)
@Composable
fun SplashScreenPreview() {
    IO_ProjectTheme {
        SplashScreen( navigateTo = {})
    }
}