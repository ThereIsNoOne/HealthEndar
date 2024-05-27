package com.slas.healthendar.datastore

import com.slas.healthendar.computation.db.FirestoreConnector
import com.slas.healthendar.controller.DatabaseController
import java.util.Calendar

object DataContext {

    val databaseController = DatabaseController(FirestoreConnector())

    fun today() : List<Int> {
        val calendar = Calendar.getInstance()

        return listOf(
            calendar.get(Calendar.DAY_OF_MONTH),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.YEAR)
        )
    }

}