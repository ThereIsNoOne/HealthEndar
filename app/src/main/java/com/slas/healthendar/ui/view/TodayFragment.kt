package com.slas.healthendar.ui.view

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.slas.healthendar.entity.ReminderDto
import com.slas.healthendar.entity.VisitDto
import com.slas.healthendar.ui.MainActivity
import com.slas.healthendar.ui.VisitActivity
import com.slas.healthendar.ui.elements.TimeLabel
import com.slas.healthendar.ui.navigation.newActivity
import com.slas.healthendar.ui.theme.Typography
import java.sql.Time
import java.time.LocalTime
import java.util.TreeMap

val reminderItems = listOf(
    ReminderDto("One", "Descrp"),
    ReminderDto("One", "Descrp"),
    ReminderDto("One", "Descrp"),
    ReminderDto("One", "Descrp"),
    ReminderDto("One", "Descrp"),
    ReminderDto("One", "Descrp"),
    ReminderDto("One", "Descrp"),
    ReminderDto("One", "Descrp")
)


val visitsMocked = TreeMap<Int, List<VisitDto>>().also {
    it[9*60] = listOf(
        VisitDto("dr. House", "Neuro", 9*60, arrayOf(2024, 4, 24), "Wroclaw", "123456789", "dr.house@house.pl" , reminderItems),
        VisitDto("dr. House", "Neuro", 9*60, arrayOf(2024, 4, 24), "Wroclaw", mail="dr.house@house.pl"),
        VisitDto("dr. House", "Neuro", 9*60, arrayOf(2024, 4, 24), "Wroclaw", "123456789"),
        VisitDto("dr. House", "Neuro", 9*60, arrayOf(2024, 4, 25), "Wroclaw", "123456789", "dr.house@house.pl"),
        VisitDto("dr. House", "Neuro", 9*60, arrayOf(2024, 4, 26), "Wroclaw", "123456789", "dr.house@house.pl")
    )
    it[600] = listOf(
        VisitDto("dr. Doolittle", "Vet", 9*60, arrayOf(2024, 4, 24),"Wroclaw", "123456789", "dr.house@house.pl"),
        VisitDto("dr. Doolittle", "Vet", 9*60, arrayOf(2024, 4, 24),"Wroclaw", mail="dr.house@house.pl"),
        VisitDto("dr. Doolittle", "Vet", 9*60, arrayOf(2024, 4, 24),"Wroclaw", "123456789"),
        VisitDto("dr. Doolittle", "Vet", 9*60, arrayOf(2024, 4, 25),"Wroclaw", "123456789", "dr.house@house.pl"),
        VisitDto("dr. Doolittle", "Vet", 9*60, arrayOf(2024, 4, 26),"Wroclaw", "123456789", "dr.house@house.pl")
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
private fun ListItem(it: VisitDto) {
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