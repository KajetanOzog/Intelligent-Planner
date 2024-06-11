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
fun AddEventPersonalComponent(
    addEventPersonalViewModel: AddEventPersonalViewModel = hiltViewModel()
) {
    val categories = listOf("Szkoła", "Inne", "Praca", "Aktywność fizyczna", "Znajomi")
    val priorities = listOf("Niski", "Średni", "Wysoki")
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
            onValueChange = addEventPersonalViewModel._changeName,
            label = "Nazwa"
        )

        OutlinedTextFieldCustom(
            onValueChange = addEventPersonalViewModel._changeDescription,
            label = "Opis"
        )

        DropDownPicker(
            onValueChange = addEventPersonalViewModel._changeCategory,
            argList = categories,
            label = "Kategoria"
        )

        DropDownPicker(
            onValueChange = addEventPersonalViewModel._changePriority,
            argList = priorities,
            label = "Priorytet"
        )

        ColorDropDownPicker(
            onValueChange = addEventPersonalViewModel._changeColor,
            argList = colors,
            label = "Kolor"
        )

        OutlinedTextFieldCustom(
            onValueChange = addEventPersonalViewModel._changePlace,
            label = "Lokalizacja"
        )

        Row() {
            DatePickerCustom(
                onValueChange = addEventPersonalViewModel._changeDate,
                label = "Data",
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TimePickerCustom(
                onValueChange = addEventPersonalViewModel._changeTime,
                label = "Godzina r.",
                modifier = Modifier.weight(1.3f)
            )
        }
        Row() {
            DatePickerCustom(
                onValueChange = addEventPersonalViewModel._changeEndDate,
                label = "Data zakończenia",
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TimePickerCustom(
                onValueChange = addEventPersonalViewModel._changeEndTime,
                label = "Godzina z.",
                modifier = Modifier.weight(1.3f)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CheckboxRow(
                onValueChange = addEventPersonalViewModel._changeReminder,
                label = "Przypomnij",
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TimePickerCustom(
                onValueChange = addEventPersonalViewModel._changeReminderTime,
                label = "O której",
                modifier = Modifier.weight(1.3f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        CheckboxRow(
            onValueChange = addEventPersonalViewModel._changeAlarm,
            label = "Alarm"
        )

        Spacer(modifier = Modifier.height(16.dp))
        CheckboxRow(
            onValueChange = addEventPersonalViewModel._changeWeekly,
            label = "Tygodniowe"
        )
        

        Spacer(modifier = Modifier.height(16.dp))
        CheckboxRow(
            onValueChange = addEventPersonalViewModel._changeVisible,
            label = "Widoczne dla znajomych"
        )
    }
}