package com.example.io_project.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.io_project.Constants.ADD_EVENT_TO_GROUP_DIALOG
import com.example.io_project.Constants.ADD_USER_TO_GROUP_DIALOG

@Composable
fun AddToGroupButton(
    modifier: Modifier = Modifier,
    groupID: String,
    navigateTo: (String) -> Unit
) {
    var extended: Boolean by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.End
    ) {
        if (extended) {
            AddButtonExtendable(
                name = "Wydarzenie",
                icon = Icons.Rounded.DateRange,
                onClick = navigateTo,
                dialogDestination = "${ADD_EVENT_TO_GROUP_DIALOG}/${groupID}"
            )
            AddButtonExtendable(
                name = "Użytkownika",
                icon = Icons.Rounded.Person,
                onClick = navigateTo,
                dialogDestination = "${ADD_USER_TO_GROUP_DIALOG}/${groupID}"
            )
        }

        ExtendedFloatingActionButton(
            text = {
                Text(
                    text = "Dodaj do grupy",
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
            onClick = { extended = !extended }
        )
    }
}