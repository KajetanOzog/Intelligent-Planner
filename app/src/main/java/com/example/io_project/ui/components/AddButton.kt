package com.example.io_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Face
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.io_project.Constants.ADD_ACTIVITY_DIALOG
import com.example.io_project.R

@Composable
fun AddButton(
    modifier: Modifier = Modifier,
    navigateTo: (String) -> Unit
) {
    var extended: Boolean by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.End
    ) {
        if (extended) {
            AddButtonExtendable(
                name = "New Activity",
                icon = Icons.Rounded.Create,
                onClick = navigateTo,
                dialogDestination = ADD_ACTIVITY_DIALOG
            )
            AddButtonExtendable(
                name = "Assistant",
                icon = Icons.Rounded.Face,
                onClick = {},
                dialogDestination = ""
            )
        }

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
            onClick = { extended = !extended }
        )
    }
}