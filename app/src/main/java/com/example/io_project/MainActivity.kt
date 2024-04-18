package com.example.io_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.io_project.ui.screens.ui.homescreen.HomeScreen
import com.example.io_project.ui.theme.IO_ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IO_ProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    IO_ProjectApp()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IO_ProjectTheme {
        IO_ProjectApp()
    }
}