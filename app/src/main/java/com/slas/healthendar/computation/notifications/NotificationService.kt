package com.slas.healthendar.computation.notifications

import android.Manifest
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.slas.healthendar.R
import com.slas.healthendar.datastore.NotificationContext

class NotificationService : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationBuilder = NotificationCompat.Builder(context, NotificationContext.CHANNEL_ID)
        notificationBuilder
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Reminder")
            .setContentText(intent.getStringExtra(NotificationContext.TEXT))
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setAutoCancel(true)

//        val channel = NotificationChannel(NotificationContext.CHANNEL_ID, NotificationContext.CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT)
//        NotificationManagerCompat.from(context).createNotificationChannel(channel)

//        val manager: NotificationManagerCompat = NotificationManagerCompat.from(context)
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.d("Permission", "Not granted, aborting")
            return
        }
        Log.d("Permission", "Granted, scheduling notification")

        manager.notify(intent.getIntExtra(NotificationContext.NOTIFICATION_ID, 0), notificationBuilder.build())
    }
}