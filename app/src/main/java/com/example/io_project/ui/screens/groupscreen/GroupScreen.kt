package com.example.io_project.ui.screens.groupscreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose.IO_ProjectTheme
import com.example.io_project.Constants.GROUP_SCREEN
import com.example.io_project.R
import com.example.io_project.dataclasses.Group
import com.example.io_project.ui.components.AddButton
import com.example.io_project.ui.components.AddToGroupButton
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.CalendarTile
import com.example.io_project.ui.components.SmallTile
import com.example.io_project.ui.components.TopBar
import com.example.io_project.ui.components.UsersColumn

@Composable
fun GroupScreen(
    modifier: Modifier = Modifier,
    groupJSON: String,
    navigateTo: (String) -> Unit,
    navigateBack: () -> Unit
) {
    val groupViewModel = GroupViewModel(groupJSON)

    Scaffold(
        topBar = {
            TopBar(
                text = groupViewModel.group.groupName,
                navigateBack = navigateBack,
                canNavigateBack = true
            )
        },
        bottomBar = {
            BottomBar(
                navigateTo = navigateTo,
                currentScreenName = GROUP_SCREEN
            )
        },
        floatingActionButton = {
            AddToGroupButton()
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
            Text(
                text = "Członkowie:",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            //TODO(Zamien na groupViewModel.members i zobacz czy wyświetla poprawnie userName dla członków grupy)
            UsersColumn(users = groupViewModel.group.groupMembers)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupScreenPreview() {
    IO_ProjectTheme {
        GroupScreen(navigateTo = {}, navigateBack = {}, groupJSON = "")
    }
}