package com.slas.healthendar.ui.view

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.slas.healthendar.entity.Reminder
import com.slas.healthendar.entity.Visit
import com.slas.healthendar.ui.VisitActivity
import com.slas.healthendar.ui.elements.TimeLabel
import com.slas.healthendar.ui.navigation.newActivity
import com.slas.healthendar.ui.theme.Typography
import java.util.TreeMap

val reminderItems = listOf(
    Reminder("One", 0f),
    Reminder("One", 0f),
    Reminder("One", 0f),
    Reminder("One", 0f),
    Reminder("One", 0f),
    Reminder("One", 0f),
    Reminder("One", 0f),
    Reminder("One", 0f)
)


val visitsMocked = TreeMap<Int, List<Visit>>().also {
    it[9*60] = listOf(
        Visit("dr. House", "Neuro", 9*60, listOf(2024, 4, 24), "Wroclaw", "123456789", "dr.house@house.pl" , reminderItems),
        Visit("dr. House", "Neuro", 9*60, listOf(2024, 4, 24), "Wroclaw", mail="dr.house@house.pl"),
        Visit("dr. House", "Neuro", 9*60, listOf(2024, 4, 24), "Wroclaw", "123456789"),
        Visit("dr. House", "Neuro", 9*60, listOf(2024, 4, 25), "Wroclaw", "123456789", "dr.house@house.pl"),
        Visit("dr. House", "Neuro", 9*60, listOf(2024, 4, 26), "Wroclaw", "123456789", "dr.house@house.pl")
    )
    it[600] = listOf(
        Visit("dr. Doolittle", "Vet", 9*60, listOf(2024, 4, 24),"Wroclaw", "123456789", "dr.house@house.pl"),
        Visit("dr. Doolittle", "Vet", 9*60, listOf(2024, 4, 24),"Wroclaw", mail="dr.house@house.pl"),
        Visit("dr. Doolittle", "Vet", 9*60, listOf(2024, 4, 24),"Wroclaw", "123456789"),
        Visit("dr. Doolittle", "Vet", 9*60, listOf(2024, 4, 25),"Wroclaw", "123456789", "dr.house@house.pl"),
        Visit("dr. Doolittle", "Vet", 9*60, listOf(2024, 4, 26),"Wroclaw", "123456789", "dr.house@house.pl")
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodayFragment(context: Context) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        visitsMocked.forEach { (time, itemList) ->
            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = TimeLabel(time),
                        fontWeight = Typography.titleMedium.fontWeight,
                        fontSize = Typography.titleMedium.fontSize,
                        fontFamily = Typography.titleMedium.fontFamily,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

            }

            items(itemList) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .clickable { newActivity(context, VisitActivity::class.java, it) }
                ) {
                    ListItem(it)
                }
            }
        }
    }
}

@Composable
private fun ListItem(it: Visit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(10.dp)
    ) {
        Text(
            text = "${it.specialization}, ${it.doctor}",
            fontWeight = Typography.labelLarge.fontWeight,
            fontSize = Typography.labelLarge.fontSize,
            fontFamily = Typography.labelLarge.fontFamily
        )
    }
}