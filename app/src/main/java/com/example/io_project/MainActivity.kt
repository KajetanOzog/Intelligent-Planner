package com.example.io_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.IO_ProjectTheme
import com.example.io_project.ui.navigation.NavGraph
import com.example.io_project.ui.screens.authscreen.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val viewModel by viewModels<AuthViewModel>()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            IO_ProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    navController = rememberNavController()
                    NavGraph(
                        navController = navController
                    )
                    checkAuthState()

                }
            }
        }
    }

    private fun checkAuthState() {
        if (viewModel.isUserAuthenticated) {
            navController.navigate("auth_screen")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IO_ProjectTheme {}
}