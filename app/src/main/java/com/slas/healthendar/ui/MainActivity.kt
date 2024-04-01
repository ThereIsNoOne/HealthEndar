package com.slas.healthendar.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.slas.healthendar.ui.elements.FloatingAddButton
import com.slas.healthendar.ui.navigation.NavItem
import com.slas.healthendar.ui.navigation.TabView
import com.slas.healthendar.ui.navigation.newActivity
import com.slas.healthendar.ui.theme.HealthEndarTheme
import com.slas.healthendar.ui.theme.Typography
import com.slas.healthendar.ui.view.CalendarFragment
import com.slas.healthendar.ui.view.RemindersFragment
import com.slas.healthendar.ui.view.TodayFragment

class MainActivity : ComponentActivity() {

    val darkTheme: Boolean = false

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val calendarTab = NavItem("Calendar", Icons.Outlined.DateRange, Icons.Filled.DateRange)
            val todayTab = NavItem("Today", Icons.Outlined.Star, Icons.Filled.Star)
            val remindersTab =
                NavItem("Reminders", Icons.Outlined.Notifications, Icons.Filled.Notifications, count = 3)

            val navItemsList = listOf(calendarTab, todayTab, remindersTab)

            val navController = rememberNavController()


            HealthEndarTheme(dynamicColor = false, darkTheme = darkTheme) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "HealthEndar",
                                    fontFamily = Typography.titleLarge.fontFamily,
                                    fontWeight = Typography.titleLarge.fontWeight,
                                    fontSize = Typography.titleLarge.fontSize,
                                    color = MaterialTheme.colorScheme.onSecondary,
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            )
                        )
                    },
                    bottomBar = {
                        TabView(
                            items = navItemsList,
                            navController = navController
                        )
                    },
                    floatingActionButton = {
                        FloatingAddButton(context = this) { newActivity(
                            context = this,
                            newActivity = AddVisitActivity::class.java
                        ) }
                    },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondaryContainer)

                ) {
                    Box(modifier = Modifier.padding(it)) {
                        NavHost(
                            navController = navController,
                            startDestination = calendarTab.title
                        ) {
                            composable(calendarTab.title) {
                                CalendarFragment(this@MainActivity, darkTheme)
                            }
                            composable(todayTab.title) {
                                TodayFragment(this@MainActivity)
                            }
                            composable(remindersTab.title) {
                                RemindersFragment()
                            }
                        }
                    }
                }

            }
        }
    }
}

