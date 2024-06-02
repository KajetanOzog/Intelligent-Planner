package com.example.io_project.ui.components

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.io_project.R
import com.example.io_project.dataclasses.Weather
import com.example.io_project.datamanagement.getIcon
import com.example.io_project.permissions.AskingForPermissions
import com.example.io_project.permissions.Permissions
import com.example.io_project.permissions.checkLocationPermission
import kotlinx.coroutines.delay

@Composable
fun WeatherWidget(modifier: Modifier = Modifier)
{
    var temp by remember { mutableStateOf(Weather.temperature) }
    var wc by remember { mutableStateOf(Weather.code) }
    val activity = LocalContext.current as Activity

    if(!AskingForPermissions.started){
        LaunchedEffect(Unit)
        {
            Permissions(activity)
            while(!AskingForPermissions.finished || !Weather.acquired && checkLocationPermission(activity))
            {
                delay(500)
            }
            temp = Weather.temperature
            wc = Weather.code
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
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
            Text(
                text = "$it°C",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White,
            )
        }
    }
}