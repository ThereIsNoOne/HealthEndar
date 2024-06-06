package com.slas.healthendar.entity

import java.io.Serializable

data class Reminder(
    val title: String ="",
    val time: Float = 0f,
    val notificationId: Int = 0
) : Serializable