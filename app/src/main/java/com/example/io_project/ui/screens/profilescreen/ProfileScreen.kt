package com.example.io_project.ui.screens.profilescreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.io_project.R
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.TopBar
import com.example.io_project.ui.components.SmallTile
import com.example.io_project.ui.screens.signinscreen.UserData
import com.example.io_project.ui.theme.IO_ProjectTheme
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navigateTo: (route: String) -> Unit,
    userData: UserData?,
    onSingOut: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                text = "Profile",
                navigateTo = navigateTo,
                canNavigateBack = true
            )
        },
        bottomBar = {
            BottomBar(
                navigateTo = navigateTo,
                currentScreenName = "profile_screen"
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(bottom = dimensionResource(id = R.dimen.padding_medium))
            ) {
                if (userData?.profilePictureUrl != null){
                    AsyncImage(
                        model = userData.profilePictureUrl,
                        contentDescription = null,
                        modifier = modifier
                            .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
                else {
                    Image(
                        painter = painterResource(id = R.drawable.default_profile_picture),
                        contentDescription = null,
                        modifier = modifier
                            .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = modifier
                        .weight(2f)
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                ) {
                    Text(
                        // upewnic sie ze to dziala gdy to jest nullem
                        text = userData?.username ?: stringResource(id = R.string.user_name),
                        style = MaterialTheme.typography.displayMedium,
                        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small))
                    )
                    Button(onClick = { onSingOut }) {
                        Text(
                            text = "Sign out",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
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





fun foo(tmp: String) {

}

@Preview(showBackground = true)
@Composable
fun ProfPreview() {
    IO_ProjectTheme {

    }
}
