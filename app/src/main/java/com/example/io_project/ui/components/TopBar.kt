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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    text: String,
    navigateTo: (route: String) -> Unit,
    canNavigateBack: Boolean
) {
    TopAppBar(
        title = {
            Text(
                text = text,
                style = MaterialTheme.typography.displayMedium
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = { navigateTo("home_screen") }
                ) {
                    Icon(
                        Icons.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }

    )
}


@Composable
fun TopBar2(
    modifier: Modifier = Modifier,
    title: String,
    navigateTo: (route: String) -> Unit,
    canNavigateBack: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(PaddingValues(dimensionResource(id = R.dimen.padding_medium)))
            .fillMaxWidth()
    ) {
        if (canNavigateBack) {
            TextButton(
                onClick = { navigateTo("home_screen") }
            ) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = null
                )
            }
        } else {
            Spacer(
                modifier = modifier.width(dimensionResource(id = R.dimen.padding_medium))
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.displayMedium
        )
    }
}
