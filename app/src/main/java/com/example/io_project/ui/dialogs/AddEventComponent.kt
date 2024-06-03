package com.example.io_project.ui.dialogs

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.io_project.R
import com.example.io_project.ui.components.CheckboxRow
import com.example.io_project.ui.components.ColorDropDownPicker
import com.example.io_project.ui.components.DatePickerCustom
import com.example.io_project.ui.components.DropDownPicker
import com.example.io_project.ui.components.OutlinedTextFieldCustom
import com.example.io_project.ui.components.TimePickerCustom

@Composable
fun AddEventComponent(
    addEventViewModel: AddEventViewModel = hiltViewModel()
) {
    val categories = listOf("Szkoła", "Praca", "Aktywność fizyczna", "Znajomi", "Inne")
    val colors = listOf(
        colorResource(id = R.color.calendar_dark_blue),
        colorResource(id = R.color.calendar_green),
        colorResource(id = R.color.calendar_orange),
        colorResource(id = R.color.calendar_red),
        colorResource(id = R.color.calendar_light_blue)
    )

    Column(
        modifier = Modifier
            .fillMaxHeight(0.7f)
            .verticalScroll(ScrollState(0))
    ) {
        OutlinedTextFieldCustom(
            onValueChange = addEventViewModel._changeName,
            label = "Nazwa"
        )

        OutlinedTextFieldCustom(
            onValueChange = addEventViewModel._changeDescription,
            label = "Opis"
        )

        DropDownPicker(
            onValueChange = addEventViewModel._changeCategory,
            argList = categories,
            label = "Kategoria"
        )

        ColorDropDownPicker(
            onValueChange = addEventViewModel._changeColor,
            argList = colors,
            label = "Kolor"
        )

        OutlinedTextFieldCustom(
            onValueChange = addEventViewModel._changePlace,
            label = "Lokalizacja"
        )

        Row() {
            DatePickerCustom(
                onValueChange = addEventViewModel._changeDate,
                label = "Data",
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TimePickerCustom(
                onValueChange = addEventViewModel._changeTime,
                label = "Godzina",
                modifier = Modifier.weight(1f)
            )
        }

        DatePickerCustom(
            onValueChange = addEventViewModel._changeEndDate,
            label = "Data zakończenia"
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CheckboxRow(
                onValueChange = addEventViewModel._changeReminder,
                label = "Przypomnij",
                modifier = Modifier.weight(2f)
            )
            TimePickerCustom(
                onValueChange = addEventViewModel._changeReminderTime,
                label = "O której",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        CheckboxRow(
            onValueChange = addEventViewModel._changeAlarm,
            label = "Alarm"
        )

        Spacer(modifier = Modifier.height(8.dp))
        CheckboxRow(
            onValueChange = addEventViewModel._changeWeekly,
            label = "Tygodniowe"
        )
        

        Spacer(modifier = Modifier.height(8.dp))
        CheckboxRow(
            onValueChange = addEventViewModel._changeVisible,
            label = "Widoczne dla znajomych"
        )
    }
}