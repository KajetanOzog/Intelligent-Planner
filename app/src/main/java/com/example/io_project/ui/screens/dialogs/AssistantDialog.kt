package com.example.io_project.ui.screens.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.compose.IO_ProjectTheme
import com.example.io_project.ui.components.DatePickerCustom
import com.example.io_project.ui.components.DropDownPicker
import com.example.io_project.ui.components.TimePickerCustom


@Composable
fun AssistantDialog(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {
    var state by remember { mutableIntStateOf(0) } // można zmieniać żeby zobaczyć podgląd danego stanu
    // 0 -> pytanie czy użytkownik ma pomysł na aktywność
    // 1 -> użytkownik nie ma pomysłu na aktywność
    // 2 -> użytkownik ma pomysł na aktywność (wybór kategorii)
        // kiedy użytkownik wybierze kategorię to przechodzimy do wyboru jak w state == 1
    // 3 -> pytanie o preferencje co do terminu
    // 4 -> brak preferencji co do terminu
    // 5 -> użytkownik ma preferencje do terminu (date picker)
    // 6 -> pytanie o zaproszenie kogoś
    // 7 -> wyświetla się lista wyboru znajomych
    // 8 -> pytanie o potwierdzenie dodania aktywności
    // 9 -> aktywność dodana
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
                text = "Asystent",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = modifier.padding(8.dp))

            when(state)
            {
                0 -> {
                    Text(
                        text = "Czy masz już pomysł na aktywność?",
                        fontSize = 14.sp
                    )
                    Spacer(modifier = modifier.padding(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        Button(
                            onClick = { state = 2 },
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Text(
                                text = "Tak",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                        Button(
                            onClick = { state = 1 },
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Text(
                                text = "Nie",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }

                1 -> {
                    Text(text = "Proponowane aktywności:", fontSize = 14.sp)
                    for(i in 1..4)
                    {
                        Row(
                            modifier = modifier
                                .padding(top = 6.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Button(
                                onClick = {
                                    state = 3
                                    // ACTIVITY CHOSEN
                                },
                                modifier = modifier
                                    .size(height = 40.dp, width = 200.dp),
                                shape = RoundedCornerShape(8.dp)
                            )
                            {
                                Text(
                                    text = "Aktywność #$i", // nazwa aktywności numer i
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = modifier
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        var refreshed by remember {mutableStateOf(false)}
                        Button(
                            onClick = {
                                // REFRESH
                                refreshed = true
                            },
                            enabled = !refreshed,
                            modifier = modifier
                                .size(height = 40.dp, width = 120.dp),
                            shape = RoundedCornerShape(8.dp)
                        )
                        {
                            Text(
                                text = "Odśwież",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                }

                2 -> {
                    Text(text = "Wybierz kategorię:", fontSize = 14.sp)
                    val categories = listOf("Szkola", "Praca", "Aktywnosc fizyczna", "Znajomi", "Inne")
                    var isEnabled by remember { mutableStateOf(false) }
                    DropDownPicker(
                        onValueChange = { isEnabled = true },
                        argList = categories,
                        label = "Kategoria"
                    )
                    Spacer(modifier = modifier.padding(10.dp))
                    Button(
                        onClick = {
                            state = 1
                        },
                        enabled = isEnabled,
                        modifier = modifier
                            .size(height = 40.dp, width = 120.dp),
                        shape = RoundedCornerShape(8.dp)
                    )
                    {
                        Text(
                            text = "Zatwierdź",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                3 -> {
                    Text(
                        text = "Czy masz preferencje co do terminu?",
                        fontSize = 14.sp
                    )
                    Spacer(modifier = modifier.padding(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        Button(
                            onClick = { state = 5 },
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Text(
                                text = "Tak",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                        Button(
                            onClick = { state = 4 },
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Text(
                                text = "Nie",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }

                4 -> {
                    /*TODO*/
                }

                5 -> {
                    var datePicked by remember { mutableStateOf(false) }
                    var timePicked by remember { mutableStateOf(false) }
                    Text(text = "Wybierz datę i godzinę:", fontSize = 14.sp)
                    Row() {
                        DatePickerCustom(
                            onValueChange = { datePicked = true }, //changeDate
                            label = "Data",
                            modifier = Modifier.weight(2f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        TimePickerCustom(
                            onValueChange = { timePicked = true }, //changeTime
                            label = "Godzina",
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = modifier.padding(10.dp))
                    Button(
                        onClick = {
                            state = 6
                        },
                        enabled = datePicked && timePicked,
                        modifier = modifier
                            .size(height = 40.dp, width = 120.dp),
                        shape = RoundedCornerShape(8.dp)
                    )
                    {
                        Text(
                            text = "Zatwierdź",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                6 -> {
                    Text(
                        text = "Czy chcesz zaprosić znajomych?",
                        fontSize = 14.sp
                    )
                    Spacer(modifier = modifier.padding(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        Button(
                            onClick = { state = 7 },
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Text(
                                text = "Tak",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                        Button(
                            onClick = { state = 8 },
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Text(
                                text = "Nie",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }

                7 -> {
                    /*TODO*/
                }

                8 -> {
                    Text(
                        text = "Czy na pewno chcesz dodać aktywność?",
                        fontSize = 14.sp
                    )
                    Spacer(modifier = modifier.padding(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        Button(
                            onClick = { state = 9 },
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Text(
                                text = "Tak",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                        Button(
                            onClick = { navigateBack() },
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Text(
                                text = "Nie",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }

                9 -> {
                    // addEvent
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AssistantDialogPreview() {
    IO_ProjectTheme {
        AssistantDialog(navigateBack = {})
    }
}