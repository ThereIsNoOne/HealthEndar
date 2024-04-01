package com.slas.healthendar.entity

import android.text.format.Time
import java.io.Serial
import java.io.Serializable
import java.util.Date
import java.util.GregorianCalendar

data class VisitDto(
    var doctor: String,
    var specialization: String,
    var time: Int,
    var date: Array<Int>,
    var localization: String,
    var phone: String? = null,
    var mail: String? = null,
    var reminders: List<ReminderDto> = ArrayList()
) : Serializable