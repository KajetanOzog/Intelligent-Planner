package com.example.intelligentplanner.dialog

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.io_project.R
import com.example.io_project.ui.components.CheckboxRow
import com.example.io_project.ui.components.DatePickerCustom
import com.example.io_project.ui.components.DropDownPicker
import com.example.io_project.ui.components.OutlinedTextFieldCustom
import java.util.Date




@Composable
fun AddEventDialog(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {
//    var eventName: String by remember { mutableStateOf("") }
//    var eventCategory: String by remember { mutableStateOf("") }
//    var eventColor: String by remember { mutableStateOf("") }
//    var eventDate: String by remember { mutableStateOf("") }
//    var eventPlace: String by remember { mutableStateOf("") }
//    var eventTime: String by remember { mutableStateOf("") }
//    var eventEndDate: String by remember { mutableStateOf("") }
//    var weeklyEvent: Boolean by remember { mutableStateOf(false) }
//    var alarmAboutEvent: Boolean by remember { mutableStateOf(false) }
//    var remindAboutEvent: Boolean by remember { mutableStateOf(false) }
//    var visibleEvent: Boolean by remember { mutableStateOf(false) }
//    var eventDescription: String by remember { mutableStateOf("") }
    val categories = listOf("Szkola", "Praca", "Aktywnosc fizyczna", "Znajomi", "Inne")
    Dialog(
        onDismissRequest = {
            navigateBack()
        }
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
                .verticalScroll(ScrollState(0))
                .background(MaterialTheme.colorScheme.onPrimaryContainer)
                .padding(16.dp)
        ) {

            Text(
                text = "Dodaj wydarzenie",
                style = MaterialTheme.typography.headlineMedium,
                color = colorResource(id = R.color.white)
            )

            Spacer(modifier = modifier.padding(8.dp))
            OutlinedTextFieldCustom(label = "Nazwa")

            DropDownPicker(argList = categories, label = "Kategoria")

            DropDownPicker(argList = categories, label = "Kolor")

            OutlinedTextFieldCustom(label = "Lokalizacja")

            Row() {
                DatePickerCustom(label = "Data",modifier = modifier.weight(1f))
                Spacer(modifier = modifier.width(8.dp))
                /*TODO TimePickerCustom*/DatePickerCustom(label = "Godzina",modifier = modifier.weight(1f))
            }

            CheckboxRow(label = "Tygodniowe")
            CheckboxRow(label = "Przypomnij")
            CheckboxRow(label = "Alarm")
            CheckboxRow(label = "Widoczne dla znajomych")

            OutlinedTextFieldCustom(label = "Opis")

            Spacer(modifier = modifier.padding(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Dodaj")
                }
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Anuluj")
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun AddEventDialogPreview() {
    AddEventDialog( navigateBack = {})
}