package com.example.io_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.DateRange
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
fun BottomBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        Icon(
            Icons.Rounded.DateRange,
            contentDescription = null
        )
        Icon(
            Icons.Rounded.Build,
            contentDescription = null
        )
        Icon(
            Icons.Rounded.AccountCircle,
            contentDescription = null
        )
        Icon(
            Icons.Rounded.Share,
            contentDescription = null
        )
        Icon(
            Icons.Rounded.Settings,
            contentDescription = null
        )
    }
}