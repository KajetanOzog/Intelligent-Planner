package com.example.io_project.ui.components

import androidx.compose.foundation.layout.PaddingValues
import com.example.io_project.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.IO_ProjectTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    text: String,
    navigateBack: () -> Unit,
    canNavigateBack: Boolean,
    refreshAction: () -> Unit = {}
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
            RefreshAction(refreshAction = refreshAction)
        }

    )
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