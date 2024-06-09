package com.example.io_project.ui.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import com.example.io_project.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.io_project.ui.navigation.changeTip
import com.example.io_project.ui.theme.IO_ProjectTheme

@Composable
fun AuthContent(
    padding: PaddingValues,
    oneTapSignIn: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {

        Text(
            text = "Witaj!",
            style = MaterialTheme.typography.headlineLarge,
        )

        TipComponent()


        SignInButton(
            onClick = oneTapSignIn
        )
    }
}

@Composable
fun TipComponent() {
    var tipNumber: Int by remember {
        mutableStateOf(1)
    }
    val context: Context = LocalContext.current
    val drawableId = remember("samouczek${tipNumber}") {
        context.resources.getIdentifier(
            "samouczek${tipNumber}",
            "drawable",
            context.packageName
        )
    }
    val stringId = remember("samouczek${tipNumber}") {
        context.resources.getIdentifier(
            "samouczek${tipNumber}",
            "string",
            context.packageName
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {


        Column {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    Icons.Rounded.KeyboardArrowLeft,
                    contentDescription = "Previous tip",
                    modifier = Modifier
                        .size(32.dp)
                        .weight(1f)
                        .clickable {
                            tipNumber = changeTip(forward = false, currentTipNumber = tipNumber)
                            Log.d("Tip", "$tipNumber, $stringId, $drawableId")
                        }
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(6f)
                ) {


                    Image(
                        painter = painterResource(id = drawableId),
                        contentDescription = "Tip screenshot",
                        modifier = Modifier
                            .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.background)
                    )
                }


                Icon(
                    Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "Next tip",
                    modifier = Modifier
                        .size(32.dp)
                        .weight(1f)
                        .clickable {
                            tipNumber = changeTip(forward = true, currentTipNumber = tipNumber)
                            Log.d("Tip", "$tipNumber, $stringId, $drawableId")
                        }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                stringResource(id = stringId),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AuthPreview() {
    IO_ProjectTheme {
        AuthContent(padding = PaddingValues()) {

        }
    }
}