package com.slas.healthendar.controller

import com.slas.healthendar.entity.Reminder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotificationsController {

    suspend fun CreateNotification(reminder: Reminder, date: List<Int>) =
        withContext(Dispatchers.Main) {

        }
}