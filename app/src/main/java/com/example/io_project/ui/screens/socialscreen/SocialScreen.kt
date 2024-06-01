package com.example.io_project.ui.screens.socialscreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose.IO_ProjectTheme
import com.example.io_project.Constants.ADD_FRIEND_DIALOG
import com.example.io_project.Constants.ADD_GROUP_DIALOG
import com.example.io_project.R
import com.example.io_project.dataclasses.Group
import com.example.io_project.datamanagement.addFriends
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.FriendDisplay
import com.example.io_project.ui.components.GroupDisplay
import com.example.io_project.ui.components.TopBar

@Composable
fun SocialScreen(
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val socialViewModel: SocialViewModel = hiltViewModel()

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
            //AddButton(navigateTo = navigateTo)
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .verticalScroll(ScrollState(0))
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {


//            Text(
//                text = "Potwierdź udział",
//                style = MaterialTheme.typography.labelLarge,
//                modifier = modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
//            )
//            SmallTile()

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
                    modifier = modifier
                        .size(32.dp)
                        .clickable {
                            navigateTo(ADD_GROUP_DIALOG)
                        }
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))

            if (socialViewModel.groupCount == 0) {
                NoGroupsText()
            } else {
                GroupsColumn(groups = socialViewModel.groups)
            }


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
                    modifier = modifier
                        .size(32.dp)
                        .clickable {
                            navigateTo(ADD_FRIEND_DIALOG)
                        }
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))

            if (socialViewModel.friendCount == 0){
                NoFriendsText()
            }
            else {
                FriendsColumn(friends = socialViewModel.friends)
            }
        }
    }
}

@Composable
fun NoGroupsText() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Brak grup do wyświetlenia")
    }
}

@Composable
fun GroupsColumn(groups: List<Group>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        groups.forEach { group ->
            GroupDisplay(group)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
        }
    }
}

@Composable
fun NoFriendsText() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Brak znajomych do wyświetlenia")
    }
}

@Composable
fun FriendsColumn(friends: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        friends.forEach { friend ->
            FriendDisplay(friend)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
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