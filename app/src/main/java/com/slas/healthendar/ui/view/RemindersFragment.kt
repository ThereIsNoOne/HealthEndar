package com.slas.healthendar.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.slas.healthendar.entity.ReminderDto
import com.slas.healthendar.ui.elements.ExpandedItem
import com.slas.healthendar.ui.elements.FoldedItem
import com.slas.healthendar.ui.theme.Typography




val items = listOf(
    ReminderDto("One", "Descrp"),
    ReminderDto("One", "Descrp"),
    ReminderDto("One", "Descrp"),
    ReminderDto("One", "Descrp"),
    ReminderDto("One", "Descrp"),
    ReminderDto("One", "Descrp"),
    ReminderDto("One", "Descrp"),
    ReminderDto("One", "Descrp")
)

@Composable
fun RemindersFragment() {
    var index by remember {
        mutableIntStateOf(0)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        items(items.size) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { index = it }
                    .clip(shape = RoundedCornerShape(20.dp))
            ) {
                if (it == index) {
                    ExpandedItem(items[it])
                } else {
                    FoldedItem(items[it])
                }
            }
        }
    }
}

