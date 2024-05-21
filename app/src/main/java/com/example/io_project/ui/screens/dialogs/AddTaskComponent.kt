package com.example.io_project.ui.screens.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.io_project.ui.components.OutlinedTextFieldCustom

@Composable
fun AddTaskComponent(
    addTaskViewModel: AddTaskViewModel
) {
    Column {
        OutlinedTextFieldCustom(
            onValueChange = addTaskViewModel._changeName,
            label = "Nazwa"
        )
    }
}