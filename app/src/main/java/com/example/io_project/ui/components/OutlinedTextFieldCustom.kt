package com.example.io_project.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.io_project.R

@Composable
fun OutlinedTextFieldCustom(
    modifier: Modifier = Modifier,
    label: String
) {
    var inputValue: String by remember { mutableStateOf("") }

    OutlinedTextField(
        value = inputValue,
        onValueChange = {
            inputValue = it
        },
        label = {Text(text = label)},

    )
}

