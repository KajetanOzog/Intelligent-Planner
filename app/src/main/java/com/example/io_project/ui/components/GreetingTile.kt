package com.example.io_project.ui.components

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.io_project.R
import kotlinx.coroutines.delay

@Composable
fun GreetingTile(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.outlineVariant)
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {

        // TO-DO:
        // 1) Dodac nazwe uzytkownika z profilu (jesli sie da to tylko imie)
        // 2) Dodac inne przywitanie w zaleznosci od godziny
        Text(
            text = stringResource(
                id = R.string.greeting_text,
                stringResource(id = R.string.user_name)
            ),
            style = MaterialTheme.typography.displayLarge,
        )

        // TO-DO:
        // 1) Sciaganie biezacej daty
        Text(
            text = "27 maj 2024",
            style = MaterialTheme.typography.labelSmall
        )
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
                    /*TO-DO: wyswietlanie podsumowania*/
                }
            )
        }

        // Tutaj Composable tworzace podsumowanie lub odwolanie to funkcji z osobnego pliku

    }
}

@Composable
fun WeatherWidget(modifier: Modifier = Modifier)
{
    var temp by remember { mutableStateOf(WeatherData.temperature) }
    var wc by remember { mutableStateOf(WeatherData.code) }
    val activity = LocalContext.current as Activity

    if(!AskingForPermissions.started){
        LaunchedEffect(Unit)
        {
            Permissions(activity)
            while(!AskingForPermissions.finished || !WeatherData.acquired && checkLocationPermission(activity))
            {
                delay(500)
            }
            temp = WeatherData.temperature
            wc = WeatherData.code
        }
    }

    wc?.let {
        Image(
            painter = painterResource(
                id = getIcon(it)
            ),
            contentDescription = "Ikona pogody",
            modifier = modifier.size(64.dp)
        )
    }
    temp?.let {
        Spacer(modifier = modifier.width(16.dp))
        Text(
            text = "$it°C",
            style = MaterialTheme.typography.labelLarge
        )
    }
}