package com.slas.healthendar.ui.view

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slas.healthendar.ui.MainActivity
import com.slas.healthendar.ui.theme.Typography
import java.sql.Time
import java.time.LocalTime

val mockedData = HashMap<String, List<String>>().also {
    it["9:00"] = listOf(
        "One",
        "Two",
        "Two",
        "Two",
        "Two",
        "Two",
        "Two",
        "Two",
        "One",
        "Two",
        "One",
        "Two",
        "One",
        "Two",
        "One",
        "Two",
        "One",
        "Two",
        "One",
        "Two",
        "One",
        "Two",
        "One",
        "Two",
        "One"
    )
    it["10:00"] = listOf(
        "Three",
        "Five",
        "Three",
        "Five",
        "Three",
        "Five",
        "Three",
        "Five",
        "Three",
        "Five",
        "Three",
        "Five",
        "Three",
        "Five",
        "Three",
        "Five",
        "Three",
        "Five",
        "Three",
        "Five",
        "Three",
        "Five",
        "Three",
        "Five"
    )
    it["9:30"] = listOf(
        "Four",
        "Six",
        "Four",
        "Six",
        "Four",
        "Six",
        "Four",
        "Six",
        "Four",
        "Six",
        "Four",
        "Six",
        "Four",
        "Six",
        "Four",
        "Six",
        "Four",
        "Six",
        "Four",
        "Six",
        "Four",
        "Six",
        "Four",
        "Six",
        "Four",
        "Six",
        "Four",
        "Six"
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodayFragment() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        mockedData.forEach { (time, name) ->
            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                ) {

                    Text(
                        text = time,
                        fontWeight = Typography.titleMedium.fontWeight,
                        fontSize = Typography.titleMedium.fontSize,
                        fontFamily = Typography.titleMedium.fontFamily,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

            }

            items(name) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = it,
                        fontWeight = Typography.labelLarge.fontWeight,
                        fontSize = Typography.labelLarge.fontSize,
                        fontFamily = Typography.labelLarge.fontFamily
                    )
                }
            }
        }
    }
}