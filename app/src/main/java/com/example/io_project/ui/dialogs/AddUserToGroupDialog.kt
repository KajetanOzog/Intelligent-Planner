package com.example.io_project.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.io_project.R
import com.example.io_project.ui.components.OutlinedTextFieldCustom

@Composable
fun AddUserToGroupDialog(
    modifier: Modifier = Modifier,
    groupID: String,
    navigateBack: () -> Unit
) {
    val addUserToGroupViewModel: AddUserToGroupViewModel = hiltViewModel()
    var userEmail by remember {
        mutableStateOf("")
    }

    Dialog(
        onDismissRequest = navigateBack
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(16.dp)
        ) {
            Text(
                text = "Dodaj znajomego",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))

            OutlinedTextFieldCustom(
                onValueChange = {
                    userEmail = it
                },
                label = "Email znajomego"
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Button(onClick = {
                    addUserToGroupViewModel.addUserToGroupByEmail(userEmail, groupID)
                    navigateBack()
                }) {
                    Text(text = "Dodaj")
                }
                Button(onClick = navigateBack) {
                    Text(text = "Anuluj")
                }
            }
        }
    }
}