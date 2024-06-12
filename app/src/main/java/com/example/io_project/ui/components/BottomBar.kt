package com.example.io_project.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.io_project.Constants.HOME_SCREEN
import com.example.io_project.R


@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    currentScreenName: String,
    navigateTo: (route: String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_small))
    ) {


        NavigationButton(
            navigateTo = navigateTo,
            targetScreenName = "$HOME_SCREEN/false",
            isCurrent = (currentScreenName == "home_screen"),
            label = "Ekran główny",
            icon = Icons.Rounded.Home,
            modifier = Modifier.weight(1f)
        )

        NavigationButton(
            navigateTo = navigateTo,
            targetScreenName = "calendar_screen",
            isCurrent = (currentScreenName == "calendar_screen"),
            label = "Kalendarz",
            icon = Icons.Rounded.DateRange,
            modifier = Modifier.weight(1f)
        )

        NavigationButton(
            navigateTo = navigateTo,
            targetScreenName = "profile_screen",
            isCurrent = (currentScreenName == "profile_screen"),
            label = "Profil",
            icon = Icons.Rounded.Person,
            modifier = Modifier.weight(1f)
        )

    }
}