package com.example.io_project.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.io_project.Constants.ADD_EVENT_DIALOG

@Composable
fun AddButton(
    modifier: Modifier = Modifier,
    navigateTo: (String) -> Unit
) {
    ExtendedFloatingActionButton(
    text = {
        Text(
            text = "Add",
            style = MaterialTheme.typography.labelSmall
        )
    },
    icon = {
        Icon(
            Icons.Rounded.Add,
            contentDescription = null
        )
    },
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = { navigateTo(ADD_EVENT_DIALOG)}
    )
}