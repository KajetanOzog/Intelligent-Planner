package com.example.io_project.ui.screens.profilescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.io_project.R
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.TopBar
import com.example.io_project.ui.components.SmallTile
import com.example.io_project.ui.components.SignOut
import com.example.io_project.ui.components.RevokeAccess
import com.example.compose.IO_ProjectTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.example.io_project.Constants.ARCHIVE_SCREEN
import com.example.io_project.Constants.REVOKE_ACCESS_MESSAGE
import com.example.io_project.Constants.SIGN_OUT
import com.example.io_project.Constants.STATS_SCREEN
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navigateTo: (route: String) -> Unit,
    navigateBack: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopBar(
                text = "Profile",
                navigateBack = navigateBack,
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
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(viewModel.photoUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = modifier
                        .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                        .height(100.dp)
                        .width(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = modifier
                        .weight(2f)
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                ) {
                    Text(
                        text = viewModel.displayName,
                        style = MaterialTheme.typography.displayMedium,
                        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small))
                    )
                    Button(onClick = { viewModel.signOut() }) {
                        Text(
                            text = "Sign out",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }

            Row() {
                SmallTile(
                    text = "Archiwum",
                    modifier = modifier
                        .padding(end = dimensionResource(id = R.dimen.padding_small))
                        .weight(1f)
                        .clickable { navigateTo(ARCHIVE_SCREEN) }
                )
                SmallTile(
                    text = "Statystyki",
                    modifier = modifier
                        .padding(start = dimensionResource(id = R.dimen.padding_small))
                        .weight(1f)
                        .clickable { navigateTo(STATS_SCREEN) }
                )
            }
            Row() {
                SmallTile(
                    text = "Eksport danych",
                    modifier = modifier
                        .padding(end = dimensionResource(id = R.dimen.padding_small))
                        .weight(1f)
                )
                SmallTile(
                    text = "Import danych",
                    modifier = modifier
                        .padding(start = dimensionResource(id = R.dimen.padding_small))
                        .weight(1f)
                )
            }
        }

    }

    SignOut(
        navigateToAuthScreen = { signedOut ->
            if (signedOut) {
                navigateTo("auth_screen")
            }
        }
    )

    fun showSnackBar() = coroutineScope.launch {
        val result = snackbarHostState.showSnackbar(
            message = REVOKE_ACCESS_MESSAGE,
            actionLabel = SIGN_OUT
        )
        if (result == SnackbarResult.ActionPerformed) {
            viewModel.signOut()
        }
    }

    RevokeAccess(
        navigateToAuthScreen = { accessRevoked ->
            if (accessRevoked) {
                navigateTo("auth_screen")
            }
        },
        showSnackBar = {
            showSnackBar()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ProfPreview() {
    IO_ProjectTheme {

    }
}

