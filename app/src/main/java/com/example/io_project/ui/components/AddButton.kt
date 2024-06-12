package com.example.io_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.io_project.Constants.ADD_ACTIVITY_DIALOG
import com.example.io_project.Constants.ASSISTANT_DIALOG
import com.example.io_project.R
import com.example.io_project.ui.theme.IO_ProjectTheme

@Composable
fun AddButton(
    modifier: Modifier = Modifier,
    navigateTo: (String) -> Unit
) {
    var extended: Boolean by remember { mutableStateOf(false) }
    val gradient = Brush.linearGradient(
        0.0f to colorResource(id = R.color.teal_700),
        0.9f to colorResource(id = R.color.teal_500),
    )

    Column(
        horizontalAlignment = Alignment.End
    ) {
        if (extended) {
            AddButtonExtendable(
                name = "Aktywność",
                icon = Icons.Rounded.Create,
                onClick = navigateTo,
                dialogDestination = ADD_ACTIVITY_DIALOG
            )
            AddButtonExtendable(
                name = "Asystent",
                icon = Icons.Rounded.Face,
                onClick = navigateTo,
                dialogDestination = ASSISTANT_DIALOG
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .shadow(shape = RoundedCornerShape(16.dp), elevation = 2.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(gradient)
                .clickable { extended = !extended }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_medium),
                        vertical = 12.dp
                    )
            ) {
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(26.dp)
                )
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
                Text(
                    text = "Dodaj",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddButtonPreview() {
    IO_ProjectTheme {
        AddButton {

        }
    }
}