package com.example.io_project.ui.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.compose.IO_ProjectTheme
import com.example.io_project.dataclasses.Event
import com.example.io_project.ui.components.EventDisplay


@Composable
fun EventDetailsDialog(
    event: Event,
    navigateBack: () -> Unit
) {
    Dialog(onDismissRequest = navigateBack) {
        Column {

        }
    }
}



@Preview (showBackground = true)
@Composable
fun EventDetailsPreview() {
    IO_ProjectTheme {
        EventDetailsDialog(
            event = Event(
                name = "Spotkanie",
                date = "sun, 2 Jun 2024",
                time = "8:00",
                category = "Szkoła",
                alarm = true
            ),
            navigateBack = {}
        )
    }
}
