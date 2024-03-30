package com.slas.healthendar.entity

import android.text.format.Time
import java.util.Date
import java.util.GregorianCalendar

data class VisitDto(
    var doctor: String,
    var specialization: String,
    var time: Int,
    var date: Array<Int>,
    var phone: String? = null,
    var mail: String? = null
)