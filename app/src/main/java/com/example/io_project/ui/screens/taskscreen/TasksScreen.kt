package com.example.io_project.ui.screens.taskscreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.IO_ProjectTheme
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.SmallTile
import com.example.io_project.ui.components.TopBar
import com.example.io_project.R

@Composable
fun TasksScreen(
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBar(
                text = "Nawyki",
                navigateTo = navigateTo,
                canNavigateBack = true
            )
        },
        bottomBar = {
            BottomBar(
                navigateTo = navigateTo,
                currentScreenName = "long_term_screen"
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .verticalScroll(ScrollState(0))
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {


            SmallTile(
                text = "Nawyk #1"
            )
            SmallTile(
                text = "Nawyk #2"
            )
            SmallTile(
                text = "Nawyk #3"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    IO_ProjectTheme {
        TasksScreen(navigateTo = {})
    }
}