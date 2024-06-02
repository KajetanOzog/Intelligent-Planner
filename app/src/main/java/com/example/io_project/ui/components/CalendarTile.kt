package com.example.io_project.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.IO_ProjectTheme
import com.example.io_project.R
import com.example.io_project.dataclasses.Event

@Composable
fun CalendarTile(events: List<Event>, modifier: Modifier = Modifier) {
    val eventsCount: Int = events.size

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .verticalScroll(ScrollState(0))
            .background(MaterialTheme.colorScheme.outlineVariant)
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {

        if (eventsCount == 0) {
            NoEventsText()
        } else {
            for (event in events) {
                EventDisplay(event = event)
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            }
        }


    }
}

@Composable
fun NoEventsText() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .fillMaxSize()
    ) {
        Text(
            text = "Brak wydarzeń na dany dzień.",
            style = MaterialTheme.typography.labelSmall
        )
    }
}


@Composable
fun EventDisplay(event: Event, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.error)
            .padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = event.name,
                style = MaterialTheme.typography.displayMedium,
                color = Color.White
            )
            if (event.alarm) {
                Icon(
                    Icons.Rounded.Notifications,
                    tint = Color.White,
                    contentDescription = "Czy ustawiony alarm"
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = event.time,
                style = MaterialTheme.typography.labelSmall,
                color = Color.White
            )
            Text(
                text = event.category,
                style = MaterialTheme.typography.labelSmall,
                color = Color.White
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun EventDisplayPreview() {
    IO_ProjectTheme {
        EventDisplay(
            event = Event(
                name = "Spotkanie",
                date = "sun, 2 Jun 2024",
                time = "8:00",
                category = "Szkoła",
                alarm = true
            )
        )
    }
}