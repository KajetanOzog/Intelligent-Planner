package com.example.io_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.io_project.R



@Composable
fun NavigationButton(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Rounded.Warning,
    navigateTo: (route: String) -> Unit,
    targetScreenName: String,
    isCurrent: Boolean
) {
    val buttonColor = if (isCurrent) MaterialTheme.colorScheme.outlineVariant
    else MaterialTheme.colorScheme.background

    val buttonModifier: Modifier = if (isCurrent) modifier
    else modifier.clickable {
        navigateTo(targetScreenName)
    }

    Row(
        modifier = buttonModifier
            .clip(RoundedCornerShape(8.dp))
            .background(buttonColor)
            .padding(horizontal = 2.dp, vertical = 1.dp)
    ) {
        Spacer(modifier = modifier.width(8.dp))
        Icon(
            icon,
            contentDescription = null
        )
        Spacer(modifier = modifier.width(8.dp))
    }

}
