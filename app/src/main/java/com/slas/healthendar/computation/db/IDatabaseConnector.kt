package com.slas.healthendar.computation.db

import com.slas.healthendar.entity.OperationResult
import com.slas.healthendar.entity.Reminder
import com.slas.healthendar.entity.Visit

interface IDatabaseConnector {

    suspend fun addVisit(userId: String, visit: Visit, onSuccess: () -> Unit, onError: (String) -> Unit)

    suspend fun updateVisit(userId: String, visit: Visit, onSuccess: () -> Unit, onError: (String) -> Unit)

    suspend fun getVisits(userId: String, callback: (String, Array<Visit>) -> Unit)

    suspend fun getVisits(userId: String, date: List<Int>, callback: (String, Array<Visit>) -> Unit)

    suspend fun getReminders(email: String, callback: (String, List<Visit>) -> Unit)

}