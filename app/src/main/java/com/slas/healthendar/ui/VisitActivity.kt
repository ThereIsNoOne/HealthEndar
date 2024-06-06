package com.slas.healthendar.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import com.slas.healthendar.datastore.DataContext
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.slas.healthendar.entity.Reminder
import com.slas.healthendar.entity.Visit
import com.slas.healthendar.ui.theme.HealthEndarTheme
import com.slas.healthendar.ui.theme.Typography
import com.slas.healthendar.ui.view.VisitFragment

class VisitActivity : ComponentActivity() {

    private lateinit var visit: Visit

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        @Suppress("DEPRECATION")
        visit = intent.getSerializableExtra("item") as Visit

        super.onCreate(savedInstanceState)
        setContent {
            HealthEndarTheme(darkTheme = false, dynamicColor = false) {
                var isDialogOpen by remember {
                    mutableStateOf(false)
                }
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "HealthEndar",
                                fontFamily = Typography.titleLarge.fontFamily,
                                fontWeight = Typography.titleLarge.fontWeight,
                                fontSize = Typography.titleLarge.fontSize,
                                color = MaterialTheme.colorScheme.onSecondary,
                            )
                        }, colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    )
                }, floatingActionButton = {
                    FloatAddReminderButton {
                        isDialogOpen = true
                    }
                }, modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)

                ) {
                    Box(modifier = Modifier.padding(it)) {
                        VisitFragment(visit = visit) {
                            if (isDialogOpen) {
                                AddReminderDialog(onConfirm = {title, time ->
                                    visit.reminders += Reminder(title, time)
                                    DataContext.databaseController.updateVisit(visit)
                                    isDialogOpen = false
                                }, onDismiss = { isDialogOpen = false })
                            }
                        }

                    }
                }
            }
        }
    }

    @Composable
    private fun AddReminderDialog(onConfirm: (String, Float) -> Unit, onDismiss: () -> Unit) {
        var title by remember {
            mutableStateOf("")
        }
        var value by remember {
            mutableStateOf(0f)
        }
        Dialog(onDismissRequest = onDismiss) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colorScheme.surface),
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        text = "Add reminder",
                        fontWeight = Typography.titleLarge.fontWeight,
                        fontSize = Typography.titleLarge.fontSize,
                        fontFamily = Typography.titleLarge.fontFamily,
                        color = MaterialTheme.colorScheme.onTertiary
                    )

                    OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Name") },
                        value = title,
                        onValueChange = { title = it })
                    Text(
                        text = "Add notification",
                        fontWeight = Typography.labelLarge.fontWeight,
                        fontSize = Typography.labelLarge.fontSize,
                        fontFamily = Typography.labelLarge.fontFamily,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                    Slider(
                        value = value,
                        onValueChange = { value = it },
                        steps = 3,
                        valueRange = 0f..50f,
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.secondary,
                            activeTrackColor = MaterialTheme.colorScheme.secondary,
                            inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                        ),
                    )
                    Text(text = decodeTime(value))

                    Row(
                        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = onDismiss) {
                            Text(text = "Cancel")
                        }
                        TextButton(onClick = { onConfirm(title, value) }) {
                            Text(text = "Ok")
                        }

                    }
                }

            }
        }
    }

    private fun decodeTime(value: Float): String {
        return when (value) {
            0f -> {
                "Week before"
            }

            12.5f -> {
                "Day before"
            }

            25f -> {
                "Hour before"
            }

            37.5f -> {
                "30 min before"
            }

            50f -> {
                "15 min before"
            }

            else -> {
                "Unknown"
            }
        }
    }

    @Composable
    private fun FloatAddReminderButton(onClick: () -> Unit) {
        FloatingActionButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add reminder",
                modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
            )
        }
    }
}
