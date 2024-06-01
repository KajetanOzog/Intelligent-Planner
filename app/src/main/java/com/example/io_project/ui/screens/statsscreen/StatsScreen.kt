package com.example.io_project.ui.screens.statsscreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.IO_ProjectTheme
import com.example.io_project.R
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.SmallTile
import com.example.io_project.ui.components.TopBar

@Composable
fun StatsScreen(
    navigateTo: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBar(
                text = "Statystyki",
                navigateBack = navigateBack,
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
            Row() {
                SmallTile(
                    text = "Wykonane zadania",
                    modifier = modifier
                        .padding(end = dimensionResource(id = R.dimen.padding_small))
                        .weight(1f)
                )
                SmallTile(
                    text = "Najdłuższa seria",
                    modifier = modifier
                        .padding(start = dimensionResource(id = R.dimen.padding_small))
                        .weight(1f)
                )
            }
            SmallTile(
                text = "Wykres kołowy"
            )
            SmallTile(
                text = "Wykres słupkowy"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatsScreenPreview() {
    IO_ProjectTheme {
        StatsScreen(navigateTo = {}, navigateBack = {})
    }
}