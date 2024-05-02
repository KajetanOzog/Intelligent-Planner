package com.example.io_project.ui.screens.longtermscreen

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
import com.example.io_project.ui.components.AddButton
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.SmallTile
import com.example.io_project.ui.components.TopBar
import com.example.io_project.ui.screens.calendarscreen.CalendarScreen
import com.example.io_project.R

@Composable
fun LongTermScreen(
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBar(
                text = "Cele długoterminowe",
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

            // TO-DO:
            // 1) Po ogarnieciu jak sciagamy dane takiego celu trzeba zrobic component
            //    ktoremy przekazemy dane takiego celu i zrobimy tu liste componentow
            //    (to samo w innych ekranach tego typu: archiwum, lista taskow)
            SmallTile(
                text = "Cel #1"
            )
            SmallTile(
                text = "Cel #2"
            )
            SmallTile(
                text = "Cel #3"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    IO_ProjectTheme {
        LongTermScreen(navigateTo = {})
    }
}