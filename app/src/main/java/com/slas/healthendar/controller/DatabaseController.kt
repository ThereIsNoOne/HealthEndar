package com.slas.healthendar.controller

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.slas.healthendar.computation.db.IDatabaseConnector
import com.slas.healthendar.entity.OperationResult
import com.slas.healthendar.entity.Visit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseController(private val connector: IDatabaseConnector) {

    suspend fun addVisit(visit: Visit, onSuccess: () -> Unit, onError: (String) -> Unit) =
        withContext(Dispatchers.Main) {
            val email = checkUser() ?: return@withContext

            connector.addVisit(email, visit, onSuccess, onError)
        }

    suspend fun getVisit(onSuccess: (Array<Visit>) -> Unit, onError: (String) -> Unit) =
        withContext(Dispatchers.Main) {
            val email = checkUser() ?: return@withContext

            connector.getVisits(email) { message, visits ->
                if (message == "Ok") {
                    onSuccess(visits)
                    return@getVisits
                }
                onError(message)
            }
        }

    suspend fun addReminder(visit: Visit, onSuccess: () -> Unit, onError: (String) -> Unit) = withContext(Dispatchers.Main){
        val email = checkUser() ?: return@withContext

        connector.updateVisit(email, visit, onSuccess, onError)
    }


    suspend fun getVisit(date: List<Int>, onSuccess: (Array<Visit>) -> Unit, onError: (String) -> Unit) =
        withContext(Dispatchers.Main) {
            val email = checkUser() ?: return@withContext

            connector.getVisits(email, date) { message, visits ->
                if (message == "Ok") {
                    onSuccess(visits)
                    return@getVisits
                }
                onError(message)
            }
        }

    private fun checkUser(): String? {
        val user = FirebaseAuth.getInstance().currentUser

        if (user == null || user.email == null) {
            return null
        }
        return user.email
    }
}