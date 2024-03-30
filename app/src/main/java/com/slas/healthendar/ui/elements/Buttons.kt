package com.slas.healthendar.ui.elements

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.slas.healthendar.R

@Composable
fun FloatingAddButton(context: Context, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier
    ) {
        Icon(
            Icons.Filled.Add,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentDescription = ContextCompat.getString(context, R.string.add_appointment)
        )
    }
}

@Composable
fun OkButton(onClick: () -> Unit) {
    TextButton(
        onClick = onClick
    ) {
        Text(
            text = "Ok",
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
fun CancelButton(onClick: () -> Unit) {
    TextButton(
        onClick = onClick
    ) {
        Text(
            text = "Cancel",
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}