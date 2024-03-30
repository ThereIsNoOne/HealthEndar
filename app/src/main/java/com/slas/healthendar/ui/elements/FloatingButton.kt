package com.slas.healthendar.ui.elements

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getString
import com.slas.healthendar.R

@Composable
fun FloatingAddButton(context: Context) {
    FloatingActionButton(
        onClick = { /*TODO*/ },
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