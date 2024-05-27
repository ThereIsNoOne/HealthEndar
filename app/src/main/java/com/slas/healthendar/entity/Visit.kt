package com.slas.healthendar.entity

import java.io.Serializable

data class Visit(
    var doctor: String = "",
    var specialization: String = "",
    var time: Int = 0,
    var date: List<Int> = listOf(0, 0, 0),
    var localization: String = "",
    var phone: String? = null,
    var mail: String? = null,
    var reminders: List<Reminder> = listOf()
) : Serializable {
    fun identifier(): String {

        return "${time}_${date[0]}${date[1]}${date[2]}"
    }
}