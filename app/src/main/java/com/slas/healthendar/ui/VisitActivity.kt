package com.slas.healthendar.ui

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.slas.healthendar.computation.notifications.NotificationService
import com.slas.healthendar.datastore.NotificationContext
import com.slas.healthendar.entity.Reminder
import com.slas.healthendar.entity.Visit
import com.slas.healthendar.ui.elements.ExpandedItem
import com.slas.healthendar.ui.elements.FoldedItem
import com.slas.healthendar.ui.navigation.newActivity
import com.slas.healthendar.ui.theme.HealthEndarTheme
import com.slas.healthendar.ui.theme.Typography
import com.slas.healthendar.ui.view.CalendarFragment
import com.slas.healthendar.ui.view.VisitFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Calendar

class VisitActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            HealthEndarTheme(darkTheme = false, dynamicColor = false) {
                var isDialogOpen by remember {
                    mutableStateOf(false)
                }
                val visit by remember {
                    mutableStateOf(intent.getSerializableExtra("item") as Visit)
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

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        val postNotificationPermission =
                            rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
                        LaunchedEffect(key1 = true) {
                            if (!postNotificationPermission.status.isGranted) {
                                postNotificationPermission.launchPermissionRequest()
                            }
                        }

                    }
                    Box(modifier = Modifier.padding(it)) {
                        VisitFragment(visit = visit) {
                            LazyColumn {
                                items(visit.reminders) {
                                    Box(
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .clip(shape = RoundedCornerShape(20.dp))
                                    ) {
                                        if (it.active && it.notificationTimestamp > Calendar.getInstance().timeInMillis) {
                                            ExpandedItem(it, this@VisitActivity) {
                                                GlobalScope.launch {

                                                    DataContext.databaseController.addReminder(
                                                        visit,
                                                        onSuccess = {
                                                            Log.d("VISIT", "Success")
                                                            newActivity(
                                                                this@VisitActivity,
                                                                VisitActivity::class.java,
                                                                visit
                                                            )
                                                            finish()
                                                        },
                                                        onError = { msg -> Log.d("VISIT", msg) })
                                                }
                                            }
                                        } else {
                                            FoldedItem(item = it)
                                        }
                                    }

                                }
                            }

                            if (isDialogOpen) {
                                AddReminderDialog(onConfirm = { title, time ->
                                    val date = Calendar.getInstance()
                                    date.set(visit.date[2], visit.date[1], visit.date[0], visit.time / 60, visit.time % 60)
                                    val reminder = Reminder(
                                        title,
                                        time,
                                        visit.time * visit.date[0] * visit.date[1] * visit.date[2] * getMs(
                                            time
                                        ) + visit.reminders.size,
                                        date.timeInMillis - getMs(time)
                                    )
                                    visit.reminders += reminder


                                    createNotification(visit, reminder)

                                    GlobalScope.launch {

                                        DataContext.databaseController.addReminder(
                                            visit,
                                            onSuccess = {
                                                Log.d("VISIT", "Success")
                                                newActivity(
                                                    this@VisitActivity,
                                                    VisitActivity::class.java,
                                                    visit
                                                )
                                                finish()
                                            },
                                            onError = { msg -> Log.d("VISIT", msg) })
                                    }

                                    isDialogOpen = false
                                }, onDismiss = { isDialogOpen = false })
                            }
                        }

                    }
                }
            }
        }
    }

    private fun createNotification(visit: Visit, reminder: Reminder) {
        val intent = Intent(applicationContext, NotificationService::class.java)
        intent.putExtra(NotificationContext.NOTIFICATION_ID, reminder.notificationId)
        intent.putExtra(NotificationContext.TEXT, reminder.title)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            reminder.notificationId,
            intent,
            PendingIntent.FLAG_MUTABLE
        )
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val date = Calendar.getInstance()
        date.set(visit.date[2], visit.date[1], visit.date[0], visit.time / 60, visit.time % 60)

        Log.d("Time", "${date.timeInMillis - getMs(reminder.time)}")

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            date.timeInMillis - getMs(reminder.time),
            pendingIntent
        )
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

    private fun getMs(value: Float): Int {
        return when (value) {
            0f -> {
                7 * 24 * 60 * 60 * 1000
            }

            12.5f -> {
                24 * 60 * 60 * 1000
            }

            25f -> {
                60 * 60 * 1000
            }

            37.5f -> {
                30 * 60 * 1000
            }

            50f -> {
                15 * 60 * 1000
            }

            else -> {
                0
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
