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
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerCustom(
    modifier: Modifier = Modifier,
    label: String
) {
    val datePickerState: DatePickerState = rememberDatePickerState()
    val calendar: Calendar = Calendar.getInstance()
    var dialogVisible: Boolean by remember { mutableStateOf(false) }
    val dateState = datePickerState.selectedDateMillis?.let {
        formatDate(it)
    } ?: ""
    var selectedDate by remember {
        mutableStateOf(formatDate(calendar.timeInMillis))
    }
    OutlinedTextField(
        value = selectedDate,
        onValueChange = {},
        enabled = false,
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
                Button(
                    onClick = {
                        selectedDate = dateState
                        dialogVisible = false
                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Wybierz")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        dialogVisible = false
                    },
                    shape = RoundedCornerShape(8.dp)
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

