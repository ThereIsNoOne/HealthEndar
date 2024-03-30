package com.slas.healthendar.ui.view

import android.view.ContextThemeWrapper
import android.view.RoundedCorner
import android.widget.CalendarView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.slas.healthendar.ui.theme.Typography
import java.util.Calendar


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarFragment(
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
                        )
                    )
                } else {
                    CalendarView(it)
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
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
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
            items(30) {
                Text(
                    text = "item no. $it",
                    fontWeight = Typography.labelLarge.fontWeight,
                    fontSize = Typography.labelLarge.fontSize,
                    fontFamily = Typography.labelLarge.fontFamily
                )
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
