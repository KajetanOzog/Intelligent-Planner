package com.example.io_project.ui.screens.socialscreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
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
import androidx.compose.ui.unit.dp
import com.example.compose.IO_ProjectTheme
import com.example.io_project.Constants.GROUP_SCREEN
import com.example.io_project.R
import com.example.io_project.ui.components.AddButton
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.CalendarTile
import com.example.io_project.ui.components.FriendButton
import com.example.io_project.ui.components.GroupButton
import com.example.io_project.ui.components.SmallTile
import com.example.io_project.ui.components.TopBar

@Composable
fun SocialScreen(
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBar(
                text = "Społeczność",
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
            AddButton(navigateTo = navigateTo)
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .verticalScroll(ScrollState(0))
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {


            Text(
                text = "Potwierdź udział",
                style = MaterialTheme.typography.labelLarge,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
            SmallTile()

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            ) {
                Text(
                    text = "Grupy",
                    style = MaterialTheme.typography.labelLarge
                )
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = "Dodaj grupę",
                    modifier = modifier.size(32.dp)
                )
            }
            GroupButton(
                navigateTo = navigateTo,
                groupID = 1,
                modifier = modifier.clickable { navigateTo(GROUP_SCREEN) }
            )
            GroupButton(
                navigateTo = navigateTo,
                groupID = 2
            )
            GroupButton(
                navigateTo = navigateTo,
                groupID = 3
            )

            Spacer(
                modifier = modifier.height(dimensionResource(id = R.dimen.padding_medium))
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            ) {
                Text(
                    text = "Znajomi",
                    style = MaterialTheme.typography.labelLarge
                )
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = "Dodaj znajomych",
                    modifier = modifier.size(32.dp)
                )
            }

            FriendButton(
                navigateTo = navigateTo,
                friendID = 1
            )
            FriendButton(
                navigateTo = navigateTo,
                friendID = 2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SocialScreenPreview() {
    IO_ProjectTheme {
        SocialScreen(navigateTo = {})
    }
}