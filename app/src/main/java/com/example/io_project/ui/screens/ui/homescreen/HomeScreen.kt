package com.example.io_project.ui.screens.ui.homescreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.CalendarTile
import com.example.io_project.ui.components.GreetingTile
import com.example.io_project.ui.components.SmallTile
import com.example.io_project.ui.components.TopBar
import com.example.io_project.R
import com.example.io_project.Screen
import com.example.io_project.ui.screens.ui.calendarscreen.CalendarScreen
import com.example.io_project.ui.theme.IO_ProjectTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateTo: (route: String) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                text = stringResource(id = R.string.app_name),
                navigateTo = navigateTo,
                canNavigateBack = false
            )
        },
        bottomBar = {
            BottomBar()
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { /*TODO*/ },
                icon = { /*TODO*/ },
                onClick = { /*TODO*/ })
        },
        modifier = modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(ScrollState(0))
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            GreetingTile()

            CalendarTile(
                modifier = modifier
                    .clickable(onClick = { navigateTo("calendar_screen") })
            )

            Column() {
                Row() {
                    SmallTile(
                        modifier
                            .padding(end = dimensionResource(id = R.dimen.padding_small))
                            .weight(1f)
                    )
                    SmallTile(
                        modifier
                            .padding(start = dimensionResource(id = R.dimen.padding_small))
                            .weight(1f)
                    )
                }
                Row() {
                    SmallTile(
                        modifier
                            .padding(end = dimensionResource(id = R.dimen.padding_small))
                            .weight(1f)
                    )
                    SmallTile(
                        modifier
                            .padding(start = dimensionResource(id = R.dimen.padding_small))
                            .weight(1f)
                    )
                }
                Row() {
                    SmallTile(
                        modifier
                            .padding(end = dimensionResource(id = R.dimen.padding_small))
                            .weight(1f)
                    )
                    SmallTile(
                        modifier
                            .padding(start = dimensionResource(id = R.dimen.padding_small))
                            .weight(1f)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    IO_ProjectTheme {
//        HomeScreen()
    }
}