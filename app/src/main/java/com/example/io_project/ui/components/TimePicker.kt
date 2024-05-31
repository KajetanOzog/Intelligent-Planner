package com.example.io_project.ui.components

import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerCustom(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    label: String
) {
    val timePickerState: TimePickerState = rememberTimePickerState(is24Hour = true)

    var dialogVisible: Boolean by remember { mutableStateOf(false) }
    val formatter = remember {
        SimpleDateFormat("HH:mm", Locale.getDefault())
    }
    val cal = Calendar.getInstance()
    var selectedTime by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = selectedTime,
        onValueChange = {},
        enabled = false,
        colors = TextFieldDefaults.colors(
            disabledTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledIndicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        label = { Text(text = label) },
        modifier = modifier.clickable {
            dialogVisible = !dialogVisible
        }
    )

    if (dialogVisible) {
        /*
            TODO: Zamiana DatePickerDialog z customowym dialogiem (Google jest na tyle miłe, że
             dodali go do dokumentacji, ale do samego Material3 już zapomnieli), bo na ten moment
             glitchuje sie okienko dialogu.
         */
        DatePickerDialog(
            onDismissRequest = {
                dialogVisible = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        cal.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                        cal.set(Calendar.MINUTE, timePickerState.minute)
                        cal.isLenient = false
                        selectedTime = formatter.format(cal.time)
                        onValueChange(selectedTime)
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
            TimePicker(
                state = timePickerState,
                layoutType = TimePickerLayoutType.Vertical
            )
        }


    }

}
