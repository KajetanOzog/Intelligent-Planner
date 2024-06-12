package com.example.io_project.ui.dialogs

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.io_project.Constants.CORRECT_DATA
import com.example.io_project.Constants.MISSING_DATA
import com.example.io_project.R
import com.example.io_project.notifications.AlarmScheduler
import com.example.io_project.notifications.AlarmSchedulerImpl
import com.example.io_project.ui.components.CheckboxRow
import com.example.io_project.ui.components.ColorDropDownPicker
import com.example.io_project.ui.components.DatePickerCustom
import com.example.io_project.ui.components.DialogPicker
import com.example.io_project.ui.components.DropDownPicker
import com.example.io_project.ui.components.OutlinedTextFieldCustom
import com.example.io_project.ui.components.TimePickerCustom
import com.example.io_project.ui.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun AddEventToGroupDialog(
    modifier: Modifier = Modifier,
    groupID: String,
    navigateBack: () -> Unit
) {
    val addEventToGroupViewModel: AddEventToGroupViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()
    val context: Context = LocalContext.current
    val alarmScheduler: AlarmScheduler = AlarmSchedulerImpl(context)

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
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(16.dp)
        ) {

            Text(
                text = "Nowa aktywność",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            val categories = listOf("Szkoła", "Inne", "Praca", "Aktywność fizyczna", "Znajomi")
            val priorities = listOf("Niski", "Średni", "Wysoki")

            Column(
                modifier = Modifier
                    .verticalScroll(ScrollState(0))
            ) {
                OutlinedTextFieldCustom(
                    onValueChange = addEventToGroupViewModel._changeName,
                    label = "Nazwa"
                )

                OutlinedTextFieldCustom(
                    onValueChange = addEventToGroupViewModel._changeDescription,
                    label = "Opis"
                )

                DropDownPicker(
                    onValueChange = addEventToGroupViewModel._changeCategory,
                    argList = categories,
                    label = "Kategoria"
                )

                DropDownPicker(
                    onValueChange = addEventToGroupViewModel._changePriority,
                    argList = priorities,
                    label = "Priorytet"
                )

                OutlinedTextFieldCustom(
                    onValueChange = addEventToGroupViewModel._changePlace,
                    label = "Lokalizacja"
                )

                Row() {
                    DatePickerCustom(
                        onValueChange = addEventToGroupViewModel._changeDate,
                        label = "Data",
                        modifier = Modifier.weight(2f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    TimePickerCustom(
                        onValueChange = addEventToGroupViewModel._changeTime,
                        label = "Godzina r.",
                        modifier = Modifier.weight(1.3f)
                    )
                }
                Row() {
                    DatePickerCustom(
                        onValueChange = addEventToGroupViewModel._changeEndDate,
                        label = "Data zakończenia",
                        modifier = Modifier.weight(2f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    TimePickerCustom(
                        onValueChange = addEventToGroupViewModel._changeEndTime,
                        label = "Godzina z.",
                        modifier = Modifier.weight(1.3f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            val errorMessage = addEventToGroupViewModel.eventAddedSuccessfully(groupID)
                            if (errorMessage == CORRECT_DATA) {
                                Toast.makeText(
                                    context,
                                    "Dodano wydarzenie",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navigateBack()
                            } else if (errorMessage == MISSING_DATA) {
                                Toast.makeText(
                                    context,
                                    "Uzupełnij wszysktie dane",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Sprawdź poprawność godzin",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

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

