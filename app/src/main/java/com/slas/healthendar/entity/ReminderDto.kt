package com.slas.healthendar.entity

import java.io.Serializable

data class ReminderDto(
    val title: String,
    val note: String
) : Serializable