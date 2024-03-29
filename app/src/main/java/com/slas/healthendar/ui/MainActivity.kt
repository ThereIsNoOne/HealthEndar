package com.slas.healthendar.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.slas.healthendar.ui.navigation.NavItem
import com.slas.healthendar.ui.navigation.TabView
import com.slas.healthendar.ui.theme.HealthEndarTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val calendarTab = NavItem("Calendar", Icons.Outlined.DateRange, Icons.Filled.DateRange)
            val todayTab = NavItem("Today", Icons.Outlined.Star, Icons.Filled.Star)
            val remindersTab = NavItem("Reminders", Icons.Outlined.Notifications, Icons.Filled.Notifications)

            val navItemsList = listOf(calendarTab, todayTab, remindersTab)

            val navController = rememberNavController()


            HealthEndarTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    TopAppBar(title = { Text(text = "HealthEndar") })

                    Scaffold(
                        bottomBar = { TabView(items = navItemsList, navController = navController)},
                        modifier = Modifier
                    ) {
                        NavHost(navController = navController, startDestination = calendarTab.title) {
                            composable(calendarTab.title) {
                                CalendarFragment()
                            }
                            composable(todayTab.title) {
                                Text(text = todayTab.title)
                            }
                            composable(remindersTab.title) {
                                Text(text = remindersTab.title)
                            }
                        }
                    }

                }
            }
        }
    }

    @Composable
    private fun CalendarFragment() {
        // TODO: Find CalendarView
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            items(10) {
                Text(text = "item no. $it")
            }
        }
    }

    @Composable
    private fun TodayFragment() {
        // TODO: Use Sticky headers
    }

    @Composable
    private fun RemindersView() {
        // TODO: LazyList
    }


}
