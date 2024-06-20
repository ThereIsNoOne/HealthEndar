package com.slas.healthendar.computation.db

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.slas.healthendar.entity.OperationResult
import com.slas.healthendar.entity.Reminder
import com.slas.healthendar.entity.Visit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirestoreConnector : IDatabaseConnector {

    private val database = Firebase.firestore
    override suspend fun addVisit(
        userId: String,
        visit: Visit,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ): Unit =
        withContext(Dispatchers.IO) {
            var persist = false
            database.collection(userId).document(visit.identifier()).get()
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        onError(task.exception!!.message.toString())
                        return@addOnCompleteListener
                    }
                    if (task.result.exists()) {
                        onError("Visit at this time already exists")
                        return@addOnCompleteListener
                    }
                    persist = true
                }.await()
            if (!persist) {
                return@withContext
            }
            try {
                database.collection(userId).document(visit.identifier()).set(visit).await()
            } catch (e: Exception) {
                Log.d("Exception", e.message.toString())
                onError(e.message.toString())
                return@withContext
            }
            onSuccess()
        }

    override suspend fun updateVisit(
        userId: String,
        visit: Visit,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        try {
            database.collection(userId).document(visit.identifier()).set(visit).await()
        } catch (e: Exception) {
            Log.d("Exception", e.message.toString())
            onError(e.message.toString())
            return@withContext
        }
        onSuccess()
    }

    override suspend fun getVisits(userId: String, callback: (String, Array<Visit>) -> Unit): Unit =
        withContext(Dispatchers.IO) {
            database.collection(userId).get()
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.d("Exception", task.exception!!.message.toString())
                        callback(task.exception!!.message.toString(), arrayOf())
                        return@addOnCompleteListener
                    }
                    callback(
                        "Ok",
                        task.result.map { it.toObject(Visit::class.java) }.toTypedArray()
                    )
                }
        }

    override suspend fun getVisits(
        userId: String,
        date: List<Int>,
        callback: (String, Array<Visit>) -> Unit
    ): Unit = withContext(Dispatchers.IO) {
        database.collection(userId).get()
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.d("Exception", task.exception!!.message.toString())
                    callback(task.exception!!.message.toString(), arrayOf())
                    return@addOnCompleteListener
                }
                callback("Ok",
                    task.result
                        .map { it.toObject(Visit::class.java) }
                        .filter { it.date == date }
                        .toTypedArray()
                )
            }
    }

    override suspend fun getReminders(email: String, callback: (String, List<Visit>) -> Unit): Unit =
        withContext(Dispatchers.IO) {
            database.collection(email).get()
                .addOnCompleteListener {task ->
                    if (!task.isSuccessful) {
                        Log.d("Exception", task.exception!!.message.toString())
                        callback(task.exception!!.message.toString(), listOf())
                        return@addOnCompleteListener
                    }
                    callback("Ok", task.result
                        .map { it.toObject(Visit::class.java) }
//                        .flatMap { it.reminders }
                        .toList())

                }
        }
}