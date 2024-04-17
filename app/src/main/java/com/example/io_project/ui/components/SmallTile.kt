package com.example.io_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.example.io_project.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun SmallTile(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Column(
        modifier = modifier
            .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
            .clip(RoundedCornerShape(16.dp))
            .aspectRatio(1.4f)
            .background(MaterialTheme.colorScheme.outlineVariant)
            .padding(dimensionResource(id = R.dimen.padding_medium))

    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displayMedium
        )
    }
}