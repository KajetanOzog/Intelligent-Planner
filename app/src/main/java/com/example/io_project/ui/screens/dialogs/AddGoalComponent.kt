package com.example.io_project.ui.screens.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.io_project.ui.components.OutlinedTextFieldCustom

@Composable
fun AddGoalComponent(
    addGoalViewModel: AddGoalViewModel
) {
    Column {
        OutlinedTextFieldCustom(
            onValueChange = addGoalViewModel._changeName,
            label = "Nazwa"
        )
    }
}