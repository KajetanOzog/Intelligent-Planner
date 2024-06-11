package com.example.io_project.ui.components

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.io_project.dataclasses.WeatherCurrent
import com.example.io_project.datamanagement.getIcon
import com.example.io_project.permissions.AskingForPermissions
import com.example.io_project.permissions.Permissions
import com.example.io_project.permissions.checkLocationPermission
import kotlinx.coroutines.delay

@Composable
fun WeatherWidget(modifier: Modifier = Modifier)
{
    var temp by remember { mutableStateOf(WeatherCurrent.temperature) }
    var wc by remember { mutableStateOf(WeatherCurrent.code) }
    val activity = LocalContext.current as Activity

    LaunchedEffect(Unit)
    {
        Permissions(activity)
        while(!AskingForPermissions.finished || !WeatherCurrent.acquired && checkLocationPermission(activity))
        {
            delay(500)
        }
        temp = WeatherCurrent.temperature
        wc = WeatherCurrent.code
    }

    Row(
        verticalAlignment = Alignment.Bottom
    ) {
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
            Column {
                Text(
                    text = "$it°C",
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}