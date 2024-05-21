package com.example.io_project.ui.screens.dialogs

import addEventToFirestore
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose.IO_ProjectTheme
import com.example.io_project.R
import com.example.io_project.ui.components.CheckboxRow
import com.example.io_project.ui.components.DatePickerCustom
import com.example.io_project.ui.components.DropDownPicker
import com.example.io_project.ui.components.OutlinedTextFieldCustom
import com.example.io_project.ui.components.TimePickerCustom
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch


@Composable
fun AddEventDialog(
    modifier: Modifier = Modifier,
    addEventViewModel: AddEventViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val addEventState by addEventViewModel.eventState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val categories = listOf("Szkola", "Praca", "Aktywnosc fizyczna", "Znajomi", "Inne")
    val colors = listOf("Czerwony, Niebieski, Zielony")

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
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(16.dp)
        ) {

            Text(
                text = "Dodaj",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = modifier.padding(4.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Zadanie",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Text(
                    text = "Wydarzenie",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Text(
                    text = "Cel",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = modifier.padding(4.dp))


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

                DropDownPicker(
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
                        modifier = modifier.weight(2f)
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    TimePickerCustom(
                        onValueChange = addEventViewModel._changeTime,
                        label = "Godzina",
                        modifier = modifier.weight(1f)
                    )
                }

                DatePickerCustom(
                    onValueChange = addEventViewModel._changeEndDate,
                    label = "Data zakończenia"
                )

                CheckboxRow(
                    onValueChange = addEventViewModel._changeWeekly,
                    label = "Tygodniowe"
                )
                CheckboxRow(
                    onValueChange = addEventViewModel._changeReminder,
                    label = "Przypomnij"
                )
                CheckboxRow(
                    onValueChange = addEventViewModel._changeAlarm,
                    label = "Alarm"
                )
                CheckboxRow(
                    onValueChange = addEventViewModel._changeVisible,
                    label = "Widoczne dla znajomych"
                )
            }

            Spacer(modifier = modifier.padding(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch{
                            addEventViewModel.addEvent()
                            Log.d("EVENT: ", addEventViewModel._eventState.toString())
                            navigateBack()
                        }

                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Dodaj",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Button(
                    onClick = {
                        navigateBack()
                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Anuluj",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddEventDialogPreview() {
    IO_ProjectTheme {
        AddEventDialog(navigateBack = {})
    }
}