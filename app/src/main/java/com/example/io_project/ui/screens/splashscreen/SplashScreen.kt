package com.example.io_project.ui.screens.splashscreen

import android.window.SplashScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

//@Composable
//fun SplashScreen(
//    navigateTo: (route: String) -> Unit,
//    modifier: Modifier = Modifier,
//    viewModel: SplashScreenViewModel = viewModel()
//) {
//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .background(color = MaterialTheme.colorScheme.background)
//            .verticalScroll(rememberScrollState()),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        CircularProgressIndicator(color = MaterialTheme.colorScheme.onBackground)
//    }
//
//    LaunchedEffect(true) {
//        delay(1000)
//        viewModel.onAppStart(navigateTo)
//    }
//}