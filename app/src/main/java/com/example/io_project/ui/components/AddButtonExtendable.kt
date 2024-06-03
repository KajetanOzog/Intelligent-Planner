package com.example.io_project.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.example.io_project.Constants
import com.example.io_project.R

@Composable
fun AddButtonExtendable(
    name: String,
    icon: ImageVector,
    onClick: (String) -> Unit,
    dialogDestination: String
) {
    Row(
        modifier = Modifier
            .shadow(elevation = dimensionResource(id = R.dimen.padding_button), shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_small)))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.padding_small)))
            .background(MaterialTheme.colorScheme.onPrimary)
            .clickable { onClick(dialogDestination) }
            .padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        Icon(icon, contentDescription = "null")
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
        Text(name)
    }
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
}