package com.example.io_project.ui.screens.splashscreeen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.io_project.ui.theme.IO_ProjectTheme
import com.example.io_project.Constants.DATE_FORMATTER_PATTERN
import com.example.io_project.Constants.SPLASH_DELAY
import com.example.io_project.datamanagement.getTasks
import com.example.io_project.datamanagement.updateTasks
import com.example.io_project.user.Settings
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun SplashScreen(
    navigateTo: (String) -> Unit
) {
    val splashViewModel: SplashViewModel = hiltViewModel()
    val context = LocalContext.current
    val dataStore = Settings(context = context)
    val summarySettings = dataStore.getSummarySettings.collectAsState(initial = false)
    val lastVisitDate = dataStore.getLastVisitDate.collectAsState(initial = "")

    LaunchedEffect(true) {
        // Przy każdym uruchomieniu aplikacji sprawdzamy czy jest to pierwsza wizyta danego dnia
        // i jesli jest, to odwiezamy wykonane i niewykonane taski oraz wyswietlamy podsumowanie
        // w przypadku takiego ustawienia w opcjach
        Firebase.auth.currentUser?.let {
            val tasksToUpdate = getTasks(it.uid)
            // delay by dac szanse pobrac dane
            delay(SPLASH_DELAY)
            if (tasksToUpdate != null)
                updateTasks(tasksToUpdate, it.uid)
        }
        Log.d("Splash", "$summarySettings, $lastVisitDate")
        val showSummary = splashViewModel.displaySummary(
            lastVisitDate.value,
            summarySettings.value
        ) {
            dataStore.saveLastVisitDate(
                LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMATTER_PATTERN))
            )
        }
        // Podobnie delay w celu odwiezenia ustawien (dataStore ma chwile zastanowienia
        // potrafi zwrocic domyslna wartosc zamiast ustawionej)
        delay(SPLASH_DELAY)
        navigateTo(splashViewModel.chooseNavigationDestination(showSummary))
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Intelligent planner",
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Bold
        )
    }
}




@Preview (showBackground = true)
@Composable
fun SplashScreenPreview() {
    IO_ProjectTheme {
        SplashScreen( navigateTo = {})
    }
}