package com.example.io_project.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.GreetingData
import com.example.io_project.datamanagement.fetchEvents
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun DaySummary(modifier: Modifier = Modifier)
{
    var events by remember { mutableStateOf(GreetingData.events) }
    LaunchedEffect(Unit) {
        if(GreetingData.events == null)
        {
            val userID = Firebase.auth.currentUser?.uid.toString()
            val formatter = DateTimeFormatter.ofPattern("EEE, MMM dd yyyy", Locale.ENGLISH)
            val date = LocalDate.now().format(formatter)
            GreetingData.events = fetchEvents(userID, date)?.toMutableList()
            Log.d("DaySummary", "Fetched Events: ${GreetingData.events}")
            for (i in 1..10)
            {
                if(GreetingData.events != null) break
                delay(500)
            }
            GreetingData.events?.let {GreetingData.events = getFourMaxPrio(it)}
            events = GreetingData.events
            for (i in 1..10)
            {
                if(events != null && events!!.size <= 4) break
                delay(500)
            }
        }
    }

    events?.let {
        for (i in it.indices)
        {
            Row(
                modifier = modifier
                    .padding(4.dp, top = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Column(modifier = modifier
                    .padding(start = 4.dp, top = 4.dp, bottom = 4.dp)
                    .fillMaxWidth(0.4F),
                )
                {
                    Text(
                        text = it[i].time,
                        modifier = Modifier.padding(start = 4.dp),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                Column(modifier = modifier
                    .padding(top = 4.dp, bottom = 4.dp)
                    .fillMaxWidth()
                )
                {
                    Text(
                        text = it[i].name,
                        modifier = Modifier.padding(end = 4.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
        ?.let {
            Row(
                modifier = modifier.padding(4.dp, top = 8.dp)
            ){}
        }
}

fun getFourMaxPrio(events: List<Event>): MutableList<Event>
{
    val timeComparator = Comparator<Event> { first, second ->
        when{
            first.time.length < second.time.length -> -1
            first.time.length > second.time.length -> 1
            first.time < second.time -> -1
            first.time > second.time -> 1
            else -> 0
        }
    }

    val prioComparator = Comparator<Event> { first, second ->
        when {
            //first.priority < second.priority -> -1
            //first.priority > second.priority -> 1
            else -> -timeComparator.compare(first, second)
        }
    }
    var sortedByPrio = events.sortedWith(prioComparator).toMutableList()
    if(sortedByPrio.size > 4)
    {
        sortedByPrio = sortedByPrio.subList(sortedByPrio.size - 4, sortedByPrio.size)
    }
    return sortedByPrio.sortedWith(timeComparator).toMutableList()
}