package com.example.io_project.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AcceptChangesButton(
    acceptChanges: () -> Unit,
    context: Context
) {
    ExtendedFloatingActionButton(
        text = {
            Text(
                text = "Zatwierdź",
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
        onClick = {
            acceptChanges()
            Toast.makeText(context, "Zatwierdzono zmiany", Toast.LENGTH_SHORT).show()
        }
    )
}