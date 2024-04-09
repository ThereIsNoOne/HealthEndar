package com.slas.healthendar.ui.view

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.RoundedCorner
import android.widget.CalendarView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.slas.healthendar.R
import com.slas.healthendar.entity.VisitDto
import com.slas.healthendar.ui.VisitActivity
import com.slas.healthendar.ui.elements.TimeLabel
import com.slas.healthendar.ui.navigation.newActivity
import com.slas.healthendar.ui.theme.Typography
import java.util.Calendar

val mockedData = listOf(
    VisitDto(
        "dr. House",
        "Neuro",
        9 * 60,
        arrayOf(2024, 4, 24),
        "Wroclaw",
        "123456789",
        "dr.house@house.pl",
        reminderItems
    ),
    VisitDto(
        "dr. House",
        "Neuro",
        9 * 60,
        arrayOf(2024, 4, 24),
        "Wroclaw",
        mail = "dr.house@house.pl"
    ),
    VisitDto("dr. House", "Neuro", 9 * 60, arrayOf(2024, 4, 24), "Wroclaw", "123456789"),
    VisitDto(
        "dr. House",
        "Neuro",
        9 * 60,
        arrayOf(2024, 4, 25),
        "Wroclaw",
        "123456789",
        "dr.house@house.pl"
    ),
    VisitDto(
        "dr. House",
        "Neuro",
        9 * 60,
        arrayOf(2024, 4, 26),
        "Wroclaw",
        "123456789",
        "dr.house@house.pl"
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarFragment(
    context: Context,
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        TitleText()
        AndroidView(
            {
                if (!darkTheme) {
                    CalendarView(
                        ContextThemeWrapper(
                            it,
                            androidx.appcompat.R.style.Base_Theme_AppCompat_Light
                        ),

                        )
                } else {
                    CalendarView(it).also {
                        // TODO: Act on date change
//                        it.setOnDateChangeListener
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            update = {
                it.date = System.currentTimeMillis()
                it.setOnDateChangeListener { _, year, month, day ->
                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, day)
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .fillMaxSize()
                .padding(10.dp),
        ) {
            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Text(
                        text = "Today",
                        fontWeight = Typography.titleLarge.fontWeight,
                        fontSize = Typography.titleLarge.fontSize,
                        fontFamily = Typography.titleLarge.fontFamily,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
            items(mockedData) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .clickable { newActivity(context, VisitActivity::class.java, it) }
                ) {
                    Text(text = TimeLabel(it.time))
                    Spacer(modifier = Modifier.weight(0.1f))
                    Text(text = "${it.specialization}, ${it.doctor}")
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}


@Composable
fun TitleText() {
    Column {
        Text(
            text = "Calendar",
            fontWeight = Typography.titleLarge.fontWeight,
            fontFamily = Typography.titleLarge.fontFamily,
            fontSize = Typography.titleLarge.fontSize
        )
        Text(
            text = "Appointments",
            fontFamily = Typography.bodyMedium.fontFamily,
            fontSize = Typography.bodyMedium.fontSize,
            fontWeight = Typography.bodyMedium.fontWeight
        )

    }

}
