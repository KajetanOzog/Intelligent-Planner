package com.example.io_project.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.LocationOn
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
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.graphics.toColorInt
import com.example.io_project.R
import com.example.compose.IO_ProjectTheme
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.EventPriority
import com.example.io_project.datamanagement.JSONToEvent
import com.example.io_project.datamanagement.fetchAllEvents
import com.example.io_project.ui.components.EventDisplay

@Composable
fun EventDetailsDialog() {
    val event = Event(
        name = "Spotkanie",
        date = "sun, 2 Jun 2024",
        time = "8:00",
        category = "Szkoła",
        alarm = true,
        color = "#FFF78C6B",
        place = "Kraków, ul. Piastowska 47",
        description = "Spotkanie mające na celu ustalenie wymagań funkcjonalnych projektu",
        priority = EventPriority.MEDIUM

    )
    val navigateBack = {}
    EventDetailsDialog(eventJSON = event.toString(), navigateBack = navigateBack)
}


@Composable
fun EventDetailsDialog(
    eventJSON: String,
    navigateBack: () -> Unit
) {
    val event = JSONToEvent(eventJSON)
    val priorityMap: Map<EventPriority, String> = mapOf(
        EventPriority.LOW to "NISKI",
        EventPriority.MEDIUM to "ŚREDNI",
        EventPriority.HIGH to "WYSOKI"
    )
    val timeString: String = event.time + if (event.endTime != "") "-" + event.endTime else ""
    Dialog(onDismissRequest = navigateBack) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color(event.color.toColorInt()))
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
                        contentDescription = "Data i czas",
                        tint = Color.White
                    )
                }
            }
            Text(
                text = event.category,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Icon(
                    Icons.Rounded.DateRange,
                    contentDescription = "Data i czas",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
                Text(
                    text = timeString,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
                Text(
                    text = event.date,
                    color = Color.White
                )
            }
            if (event.place != "") {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Icon(
                        Icons.Rounded.LocationOn,
                        contentDescription = "Data i czas",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
                    Text(
                        text = event.place,
                        color = Color.White
                    )
                }
            }


            if (event.description != "") {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
                Row {
                    Icon(
                        Icons.Rounded.Create,
                        contentDescription = "Data i czas",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
                    Text(
                        text = event.description,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Priorytet: ${priorityMap[event.priority]}",
                    color = Color.White
                )
                Icon(
                    Icons.Rounded.Clear,
                    contentDescription = "Zamknij",
                    tint = Color.White,
                    modifier = Modifier.clickable { navigateBack() }
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun EventDetailsPreview() {
    IO_ProjectTheme {
        EventDetailsDialog()
    }
}
