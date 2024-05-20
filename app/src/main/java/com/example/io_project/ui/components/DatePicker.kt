package com.example.io_project.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerCustom(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    label: String
) {
    val datePickerState: DatePickerState = rememberDatePickerState()
    val calendar: Calendar = Calendar.getInstance()
    var dialogVisible: Boolean by remember { mutableStateOf(false) }
    val dateState = datePickerState.selectedDateMillis?.let {
        formatDate(it)
    } ?: ""
    var selectedDate by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = selectedDate,
        onValueChange = {},
        enabled = false,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
        ),
        label = { Text(text = label) },
        modifier = modifier.clickable {
            dialogVisible = !dialogVisible
        }
    )

    if (dialogVisible) {
        DatePickerDialog(
            onDismissRequest = {
                dialogVisible = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        selectedDate = dateState
                        onValueChange(dateState)
                        dialogVisible = false
                    }
                ) {
                    Text("Wybierz")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        dialogVisible = false
                    }
                ) {
                    Text("Anuluj")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

fun formatDate(millis: Long): String {
    return SimpleDateFormat("EEE, MMM d yyyy", Locale.ENGLISH).format(millis)
}

