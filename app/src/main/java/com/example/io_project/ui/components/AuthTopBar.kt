package com.example.io_project.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTopBar(modifier: Modifier = Modifier) {
    TopAppBar (
        title = {
            Text(
                text = "",
                style = MaterialTheme.typography.displayMedium,
                modifier = modifier.fillMaxWidth()
            )
        }
    )
}