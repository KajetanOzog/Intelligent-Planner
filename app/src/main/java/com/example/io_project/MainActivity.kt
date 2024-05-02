package com.example.io_project

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.io_project.ui.navigation.NavGraph
import com.example.io_project.ui.screens.authscreen.AuthViewModel
import com.example.io_project.ui.screens.calendarscreen.CalendarScreen
import com.example.io_project.ui.screens.homescreen.HomeScreen
import com.example.io_project.ui.screens.profilescreen.ProfileScreen
import com.example.io_project.ui.screens.signinscreen.GoogleAuthUiClient
import com.example.io_project.ui.screens.signinscreen.SignInScreen
import com.example.io_project.ui.screens.signinscreen.SignInViewModel
import com.example.compose.IO_ProjectTheme
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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