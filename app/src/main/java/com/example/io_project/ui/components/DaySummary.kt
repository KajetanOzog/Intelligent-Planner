package com.example.io_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.io_project.dataclasses.Event

// Displays today's events in greeting tile
@Composable
fun DaySummary(
    modifier: Modifier = Modifier,
    events: List<Event>
)
{
    for (i in events.indices)
    {
        Row(
            modifier = modifier
                .padding(4.dp, top = 8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(events[i].color.toColorInt())),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Column(modifier = modifier
                .padding(start = 4.dp, top = 4.dp, bottom = 4.dp)
                .fillMaxWidth(0.4F),
            )
            {
                Text(
                    text = events[i].time,
                    modifier = Modifier.padding(start = 4.dp),
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )
            }
            Column(modifier = modifier
                .padding(top = 4.dp, bottom = 4.dp)
                .fillMaxWidth()
            )
            {
                Text(
                    text = events[i].name,
                    modifier = Modifier.padding(end = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )
            }
        }
    }
}

