package com.example.io_project.ui.components

import com.example.io_project.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.io_project.ui.theme.IO_ProjectTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    text: String,
    navigateBack: () -> Unit,
    canNavigateBack: Boolean,
    refreshAction: () -> Unit = {},
    showRefresh: Boolean = false,
    otherEvents: () -> Unit = {},
    showVisibility: Boolean = false
) {
    TopAppBar(
        title = {
            Row {
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
                Text(
                    text = text,
                    style = MaterialTheme.typography.displayMedium
                )
            }
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = { navigateBack() }
                ) {
                    Icon(
                        Icons.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        actions = {
            if (showVisibility) {
                VisibilityAction(otherEvents)
            }
            if (showRefresh) {
                RefreshAction(refreshAction = refreshAction)
            }
        }
    )
}


@Composable
fun VisibilityAction(changeVisibility: () -> Unit) {
    var showEvents by remember {
        mutableStateOf(false)
    }
    val color = if (showEvents) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inverseOnSurface

    IconButton(onClick = {
        changeVisibility()
        showEvents = !showEvents
    }) {
        Icon(
            Icons.Rounded.AccountCircle,
            contentDescription = "Change visibility of other events",
            tint = color
        )
    }
}


@Composable
fun RefreshAction(refreshAction: () -> Unit) {
    IconButton(onClick = { refreshAction() }) {
        Icon(
            Icons.Rounded.Refresh,
            contentDescription = "Refresh data",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    IO_ProjectTheme {
        TopBar(text = "Intelligent planner", navigateBack = {}, canNavigateBack = false)
    }
}