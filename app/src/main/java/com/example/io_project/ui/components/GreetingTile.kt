package com.example.io_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.io_project.ui.theme.IO_ProjectTheme
import com.example.io_project.R
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.GreetingData
import com.example.io_project.datamanagement.isItDayTime
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// Displays the tile at the top of home screen
@Composable
fun GreetingTile(
    modifier: Modifier = Modifier,
    defaultSummaryVisibility: Boolean,
    events: List<Event>
) {
    val defaultBool: Boolean = defaultSummaryVisibility
    val dayGradient = Brush.verticalGradient(
        colors = listOf(
            colorResource(id = R.color.light_blue_600),
            colorResource(id = R.color.day_sky_blue)
        ),
        tileMode = TileMode.Clamp,

    )
    val eveningGradient = Brush.verticalGradient(
        colors = listOf(
            colorResource(id = R.color.evening_sky_blue),
            colorResource(id = R.color.evening_sky_purple)
        ),
        tileMode = TileMode.Clamp
    )


    var isSummaryVisible by remember { mutableStateOf(defaultBool) }
    val backGroundColor = if (isItDayTime()) dayGradient else eveningGradient
    val dropDownIcon =
        if (isSummaryVisible) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown

    Column(
        modifier = modifier
            .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(backGroundColor)
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {

        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_button))
        ) {
            Greeting()
            DateDisplay()
        }

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
                dropDownIcon,
                contentDescription = "Rozwin podsumowanie dnia",
                tint = Color.White,
                modifier = modifier.clickable {
                    isSummaryVisible = !isSummaryVisible
                }
            )
        }

        if (isSummaryVisible) {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            DaySummary(modifier = modifier, events = events)
        }
    }
}

// Displays a time-dependent greeting and user name
@Composable
fun Greeting() {
    var time by remember { mutableStateOf<Int?>(null) }
    var displayName by remember { mutableStateOf(GreetingData.displayName) }
    LaunchedEffect(Unit)
    {
        val formatter = DateTimeFormatter.ofPattern("HH")
        time = LocalDateTime.now().format(formatter).toInt()
        GreetingData.displayName = Firebase.auth.currentUser?.displayName.toString().substringBefore(" ")
        displayName = GreetingData.displayName
    }
    time?.let {
        if (it >= 19 || it <= 3) {
            GreetingData.greetingText = "Dobry wieczór"
        }
    }

    displayName?.let {
        Text(
            text = "${GreetingData.greetingText}, $it!",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.displayLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
        ?: run {
            Text(
                text = "${GreetingData.greetingText}!",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.displayLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }


}

// Displays current date
@Composable
fun DateDisplay() {
    var date by remember { mutableStateOf(GreetingData.date) }
    LaunchedEffect(Unit)
    {
        if (date == null) {
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            GreetingData.date = LocalDate.now().format(formatter)
            date = GreetingData.date
        }
    }

    date?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IO_ProjectTheme {
        GreetingTile(defaultSummaryVisibility = true, events = emptyList())
    }
}