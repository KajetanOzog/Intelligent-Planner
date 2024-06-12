package com.example.io_project.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize


@Composable
fun DropDownPicker(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    argList: List<String>,
    label: String,
) {
    var selectedValue by remember { mutableStateOf(argList[1]) }
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val expansionIcon =
        if (expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown

    Box {
        OutlinedTextField(
            value = selectedValue,
            label = { Text(text = label) },
            onValueChange = {
                selectedValue = it
                onValueChange(it)
            },
            colors = TextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledIndicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            enabled = false,
            trailingIcon = {
                Icon(
                    expansionIcon,
                    contentDescription = null
                )
            },
            modifier = Modifier
                .clickable { expanded = !expanded }
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
            )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            argList.forEach { itemName ->
                DropdownMenuItem(
                    text = { Text(text = itemName) },
                    onClick = {
                        selectedValue = itemName
                        onValueChange(itemName)
                        expanded = false
                    }
                )
            }
        }
    }


}
