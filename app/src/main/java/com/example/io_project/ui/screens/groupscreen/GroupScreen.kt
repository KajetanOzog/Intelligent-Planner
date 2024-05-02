package com.example.io_project.ui.screens.groupscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.IO_ProjectTheme
import com.example.io_project.R
import com.example.io_project.ui.components.AddButton
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.CalendarTile
import com.example.io_project.ui.components.SmallTile
import com.example.io_project.ui.components.TopBar

@Composable
fun GroupScreen(
    modifier: Modifier = Modifier,
    groupID: Int = 0,
    navigateTo: (String) -> Unit
) {
    Scaffold(
        // TO-DO:
        // 1) Nawigacja do ekranu spolecznosci a nie do homescreen
        topBar = {
            TopBar(
                text = "Grupa #1",
                navigateTo = navigateTo,
                canNavigateBack = true
            )
        },
        bottomBar = {
            BottomBar(
                navigateTo = navigateTo,
                currentScreenName = "long_term_screen"
            )
        },
        floatingActionButton = {
            AddButton()
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
            ) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = "Poprzedni dzień"
                )
                Text(
                    text = "27 maj 2024",
                    style = MaterialTheme.typography.displayMedium
                )
                Icon(
                    Icons.Rounded.ArrowForward,
                    contentDescription = "Poprzedni dzień"
                )
            }
            CalendarTile(
                modifier = modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
                    .aspectRatio(0.9f)
            )
            Row() {
                SmallTile(
                    text = "Członkowie",
                    modifier = modifier
                        .padding(end = dimensionResource(id = R.dimen.padding_small))
                        .weight(1f)
                )
                SmallTile(
                    text = "Zarządzaj grupą",
                    modifier = modifier
                        .padding(start = dimensionResource(id = R.dimen.padding_small))
                        .weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupScreenPreview() {
    IO_ProjectTheme {
        GroupScreen(navigateTo = {})
    }
}