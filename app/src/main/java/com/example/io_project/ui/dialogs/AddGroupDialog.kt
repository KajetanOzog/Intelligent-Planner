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
import androidx.compose.ui.Alignment
import com.example.io_project.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.io_project.ui.theme.IO_ProjectTheme
import com.example.io_project.ui.components.OutlinedTextFieldCustom

@Composable
fun AddGroupDialog(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {
    val addGroupViewModel: AddGroupViewModel = hiltViewModel()

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
                text = "Stwórz nową grupę",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))

            OutlinedTextFieldCustom(
                onValueChange = addGroupViewModel._changeName,
                label = "Nazwa grupy"
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Button(onClick = {
                    addGroupViewModel.addNewGroup()
                    navigateBack()
                }) {
                    Text(text = "Stwórz")
                }
                Button(onClick = navigateBack) {
                    Text(text = "Anuluj")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddGroupPreview() {
    IO_ProjectTheme {
        AddGroupDialog(navigateBack = {})
    }
}