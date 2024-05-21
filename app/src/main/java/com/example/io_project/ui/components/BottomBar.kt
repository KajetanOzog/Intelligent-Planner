package com.example.io_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.res.dimensionResource
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
            targetScreenName = "home_screen",
            isCurrent = (currentScreenName == "home_screen"),
            label = "Home",
            icon = Icons.Rounded.Home
        )

        NavigationButton(
            navigateTo = navigateTo,
            targetScreenName = "calendar_screen",
            isCurrent = (currentScreenName == "calendar_screen"),
            label = "Calendar",
            icon = Icons.Rounded.DateRange
        )

        NavigationButton(
            navigateTo = navigateTo,
            targetScreenName = "profile_screen",
            isCurrent = (currentScreenName == "profile_screen"),
            label = "Profile",
            icon = Icons.Rounded.Person
        )


//        Icon(
//            Icons.Rounded.Home,
//            contentDescription = null,
//            modifier = modifier.clickable {
//                navigateTo("home_screen")
//            }
//        )
//        Icon(
//            Icons.Rounded.DateRange,
//            contentDescription = null,
//            modifier = modifier.clickable {
//                navigateTo("calendar_screen")
//            }
//        )
//        Icon(
//            Icons.Rounded.AccountCircle,
//            contentDescription = null,
//            modifier = modifier.clickable {
//                navigateTo("profile_screen")
//            }
//        )
    }
}