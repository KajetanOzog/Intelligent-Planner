package com.example.io_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.io_project.R



@Composable
fun NavigationButton(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Rounded.Warning,
    navigateTo: (route: String) -> Unit,
    targetScreenName: String,
    label: String,
    isCurrent: Boolean
) {
    val buttonColor = if (isCurrent) MaterialTheme.colorScheme.outlineVariant
    else MaterialTheme.colorScheme.background

    val buttonModifier: Modifier = if (isCurrent) modifier
    else modifier.clickable {
        navigateTo(targetScreenName)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = buttonModifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(buttonColor)
                .padding(horizontal = 2.dp, vertical = 1.dp)
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                icon,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }

}
