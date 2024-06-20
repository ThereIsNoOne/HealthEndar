package com.slas.healthendar.ui.elements

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.slas.healthendar.computation.notifications.NotificationService
import com.slas.healthendar.datastore.NotificationContext
import com.slas.healthendar.entity.Reminder
import com.slas.healthendar.entity.Visit
import com.slas.healthendar.ui.theme.Typography
import java.util.Calendar

@Composable
fun ExpandedItem(
    item: Reminder,
    context: Context,
    callback: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .wrapContentHeight()
            .padding(10.dp)
    ) {
        Text(
            text = item.title,
            fontWeight = Typography.titleMedium.fontWeight,
            fontSize = Typography.titleMedium.fontSize,
            fontFamily = Typography.titleMedium.fontFamily,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Button(
                onClick = {
                    cancel(item, context)
                    createNotification(item, context)
                    callback()
                    Toast.makeText(context, "Reminder delayed 15 min", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Remind me later",
                    fontWeight = Typography.labelLarge.fontWeight,
                    fontSize = Typography.labelLarge.fontSize,
                    fontFamily = Typography.labelLarge.fontFamily,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }

            Box(modifier = Modifier.weight(0.1f))

            Button(
                onClick = {
                    cancel(item, context)
                    item.active = false
                    callback()
                },
                modifier = Modifier
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Done",
                    fontWeight = Typography.labelLarge.fontWeight,
                    fontSize = Typography.labelLarge.fontSize,
                    fontFamily = Typography.labelLarge.fontFamily,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
    }
}

fun createNotification(reminder: Reminder, context: Context) {
    val intent = Intent(context.applicationContext, NotificationService::class.java)
    intent.putExtra(NotificationContext.NOTIFICATION_ID, reminder.notificationId)
    intent.putExtra(NotificationContext.TEXT, reminder.title)
    val pendingIntent = PendingIntent.getBroadcast(
        context.applicationContext,
        reminder.notificationId,
        intent,
        PendingIntent.FLAG_MUTABLE
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    var time = reminder.notificationTimestamp
    time += 15 * 60 * 1000
    reminder.notificationTimestamp = time
    Log.d("NewTime", "${reminder.notificationTimestamp}")

    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        reminder.notificationTimestamp,
        pendingIntent
    )
}

fun cancel(reminder: Reminder, context: Context) {
    val intent = Intent(context.applicationContext, NotificationService::class.java)
    intent.putExtra(NotificationContext.NOTIFICATION_ID, reminder.notificationId)
    intent.putExtra(NotificationContext.TEXT, reminder.title)
    val pendingIntent = PendingIntent.getBroadcast(
        context.applicationContext,
        reminder.notificationId,
        intent,
        PendingIntent.FLAG_MUTABLE
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    Log.d("Canceled", "Canceled alarm")
    alarmManager.cancel(pendingIntent)
}

@Composable
fun FoldedItem(
    item: Reminder,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(10.dp)
    ) {
        Text(
            text = item.title,
            fontWeight = Typography.titleMedium.fontWeight,
            fontSize = Typography.titleMedium.fontSize,
            fontFamily = Typography.titleMedium.fontFamily,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
