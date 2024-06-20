package com.slas.healthendar.entity

import java.io.Serializable
import java.util.Calendar

data class Reminder(
    val title: String ="",
    val time: Float = 0f,
    val notificationId: Int = 0,
    var notificationTimestamp: Long = 0L,
    var active: Boolean = true
) : Serializable {

}