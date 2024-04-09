package com.slas.healthendar.ui.elements

import java.text.DecimalFormat
import java.text.NumberFormat

fun TimeLabel(time: Int) : String {
    val format: NumberFormat = DecimalFormat("00")
    return "${format.format(time / 60)}:${format.format(time % 60)}"
}