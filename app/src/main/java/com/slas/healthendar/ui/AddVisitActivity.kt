package com.slas.healthendar.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.slas.healthendar.R
import com.slas.healthendar.ui.fragments.MainFragment
import com.slas.healthendar.ui.fragments.ReminderFragment
import com.slas.healthendar.ui.fragments.TodayFragment
import com.slas.healthendar.ui.theme.HealthEndarTheme
import com.slas.healthendar.ui.theme.Typography
import com.slas.healthendar.ui.view.AddVisitFragment
import com.slas.healthendar.ui.view.VisitFragment

class AddVisitActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HealthEndarTheme(darkTheme = false, dynamicColor = false) {
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

                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondaryContainer)

                ) {
                    Box(modifier = Modifier.padding(it)) {
                        AddVisitFragment(this@AddVisitActivity)

                    }
                }
            }
        }

//        setContentView(R.layout.appointment_details)
//
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.add_appointment)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//
//            insets
//        }
    }
}