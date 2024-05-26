package com.example.io_project.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.IO_ProjectTheme
import com.example.io_project.R
import com.example.io_project.dataclasses.GreetingData
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun GreetingTile(modifier: Modifier = Modifier)
{
    var isSummaryVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.outlineVariant)
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {

        Greeting()

        DateDisplay()

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                WeatherWidget(modifier)
            }

            Icon(
                Icons.Rounded.KeyboardArrowDown,
                contentDescription = "Rozwin podsumowanie dnia",
                modifier = modifier.clickable {
                    isSummaryVisible = !isSummaryVisible
                }
            )
        }
        if(isSummaryVisible) DaySummary(modifier)
    }
}

@Composable
fun Greeting()
{
    var time by remember { mutableStateOf<Int?>(null) }
    LaunchedEffect(Unit)
    {
        val formatter = DateTimeFormatter.ofPattern("HH")
        time = LocalDateTime.now().format(formatter).toInt()
    }
    time?.let {
        if(it >= 19 || it <= 3){
            GreetingData.greetingText = "Dobry wieczór"
        }
    }
    // TO-DO:
    // 1) Dodac nazwe uzytkownika z profilu (jesli sie da to tylko imie)
    Text(
        text = "${GreetingData.greetingText}, Michał",
        style = MaterialTheme.typography.displayLarge,
    )
}

@Composable
fun DateDisplay()
{
    var date by remember { mutableStateOf(GreetingData.date) }
    LaunchedEffect(Unit)
    {
        if(date == null)
        {
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            GreetingData.date = LocalDate.now().format(formatter)
            while(GreetingData.date == null){
                Log.d("date", "delaying")
                delay(500)
            }
            date = GreetingData.date
        }
    }

    date?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IO_ProjectTheme {
        GreetingTile()
    }
}