package com.example.io_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.io_project.R

@Composable
fun GroupButton(
    navigateTo: (String) -> Unit,
    groupID: Int = 0,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(bottom = dimensionResource(id = R.dimen.padding_small))
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.outlineVariant)
            .padding(dimensionResource(id = R.dimen.padding_small))
            .clickable { navigateTo("group_screen") }
    ) {
        Text(
            text = "Grupa #${groupID}",
            style = MaterialTheme.typography.labelSmall
        )
    }
}