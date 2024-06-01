package com.example.io_project.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.io_project.R

@Composable
fun UsersColumn(users: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        users.forEach { user ->
            UserDisplay(user)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
        }
    }
}