package com.example.io_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.io_project.R
import com.example.io_project.dataclasses.Event
import com.example.io_project.datamanagement.fetchEvents
import com.example.io_project.ui.screens.calendarscreen.getCurrentDate
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun CalendarTile(date: String? = getCurrentDate(LocalDate.now()), modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.outlineVariant)
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        Text(
            text = "Kalendarz",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .padding(start = 16.dp, top = 5.dp, bottom = 15.dp)
        )

        var list: List<Event>? = null

        if (date != null) {
            LaunchedEffect(Unit){
                Firebase.auth.currentUser?.let {
                    list = fetchEvents(
                        userID = it.uid,
                        targetDate = date
                    )
                }
            }
        }

        var i = 0
        while ((i < 3) && (i < (list?.size ?: 3))) {
            if (date != null) {
                EventDisplay(list?.get(i)?.date ?: "data", list?.get(i)?.name ?: "nazwa")
            }
            i++
        }

    }
}

@Composable
fun EventDisplay(date: String, eventName: String, modifier: Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 30.dp)
    ) {
        Row (
            modifier = Modifier
                .align(Alignment.Start)
        ) {
            Text(
                text = date,
                style = MaterialTheme.typography.labelSmall
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Text(
                text = eventName,
                style = MaterialTheme.typography.displayMedium
            )
        }
    }
}