package com.example.io_project.ui.screens.ui.profilescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.TopBar
import com.example.io_project.R
import com.example.io_project.Screen
import com.example.io_project.ui.components.SmallTile
import com.example.io_project.ui.theme.IO_ProjectTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navigateTo: (route: String) -> Unit
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
                Image(
                    painter = painterResource(id = R.drawable.default_profile_picture),
                    contentDescription = null,
                    modifier = modifier
                        .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                        .clip(CircleShape)

                )
                Column(
                    modifier = modifier
                        .weight(2f)
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                ) {
                    Text(
                        text = stringResource(id = R.string.user_name),
                        style = MaterialTheme.typography.displayMedium,
                        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small))
                    )
                    Button(onClick = { /*TODO*/ }) {
                        Text(
                            text = "Log out",
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
        ProfileScreen {
            foo("yo")
        }
    }
}
