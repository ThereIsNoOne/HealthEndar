package com.slas.healthendar.ui.view

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.slas.healthendar.datastore.DataContext
import com.slas.healthendar.entity.Visit
import com.slas.healthendar.ui.MainActivity
import com.slas.healthendar.ui.elements.CancelButton
import com.slas.healthendar.ui.elements.OkButton
import com.slas.healthendar.ui.elements.TimeLabel
import com.slas.healthendar.ui.elements.TimePickerDialog
import com.slas.healthendar.ui.navigation.newActivity
import com.slas.healthendar.ui.icon.rememberAlarmAdd
import com.slas.healthendar.ui.theme.Typography
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Duration
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun AddVisitFragment(context: Context) {
    var specialization by remember {
        mutableStateOf("")
    }
    var doctor by remember {
        mutableStateOf("")
    }

    var localization by remember {
        mutableStateOf("")
    }
    var time by remember {
        mutableIntStateOf(0)
    }

    var date by remember {
        mutableStateOf(Calendar.getInstance())
    }

    var phone by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var datePickerState = rememberDatePickerState()
    var showDatePicker by remember {
        mutableStateOf(false)
    }
    val timePickerState = rememberTimePickerState()
    var showTimePicker by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            maxLines = 1,
            label = { Text(text = "Specialization") },
            value = specialization,
            onValueChange = { specialization = it })

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Doctor") },
            singleLine = true,
            value = doctor,
            maxLines = 1,
            onValueChange = { doctor = it })

        TimeInput(time) { showTimePicker = true }

        DateInput(date) { showDatePicker = true }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            label = { Text(text = "Phone") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = phone,
            onValueChange = { phone = it })

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            label = { Text(text = "E-Mail") },
            value = email,
            onValueChange = { email = it })

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            maxLines = 1,
            label = { Text(text = "Localization") },
            singleLine = true,
            value = localization,
            onValueChange = { localization = it })

        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            OkButton {
                val visit = packVisit(specialization, doctor, time, localization, date, phone, email)
                if (visit.doctor.isBlank()) {
                    Toast.makeText(context, "Doctor name is blank", Toast.LENGTH_SHORT).show()
                    return@OkButton
                }

                if (visit.specialization.isBlank()) {
                    Toast.makeText(context, "Doctor specialization is blank", Toast.LENGTH_SHORT).show()
                    return@OkButton
                }

                if (visit.localization.isBlank()) {
                    Toast.makeText(context, "Localization is blank", Toast.LENGTH_SHORT).show()
                    return@OkButton
                }
                GlobalScope.launch {
                    DataContext.databaseController.addVisit(
                        visit = visit,
                        onSuccess = {
//                            Toast.makeText(context, "Visit created", Toast.LENGTH_SHORT).show()
                            newActivity(context, MainActivity::class.java)
                        },
                        onError = {
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            Log.d("AddingVisit", it)
                        })
                }
            }
        }

        if (showTimePicker) {
            TimePickerDialog(
                onDismissRequest = { },
                confirmButton = {
                    OkButton {
                        showTimePicker = false
                        time = timePickerState.hour * 60 + timePickerState.minute
                    }
                },
                dismissButton = { CancelButton { showTimePicker = false } }
            )
            {
                TimePicker(state = timePickerState)
            }

        }

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { /*TODO*/ },
                confirmButton = {
                    OkButton {
                        showDatePicker = false
                        date = Calendar.getInstance()
                            .apply {
                                if (datePickerState.selectedDateMillis == null) {
                                    return@apply
                                }
                                timeInMillis = datePickerState.selectedDateMillis!!
                            }
                    }
                },
                dismissButton = {
                    CancelButton {
                        showDatePicker = false
                    }
                }
            )
            {
                DatePicker(state = datePickerState)
            }
        }
    }

}

fun packVisit(
    specialization: String,
    doctor: String,
    time: Int,
    localization: String,
    date: Calendar,
    phone: String,
    email: String
): Visit {
    return Visit(
        doctor,
        specialization,
        time,
        listOf(date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.MONTH), date.get(Calendar.YEAR)),
        localization,
        phone,
        email,
    )
}

@Composable
fun TimeInput(time: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Time:",
            fontWeight = Typography.labelLarge.fontWeight,
            fontSize = Typography.labelLarge.fontSize,
            fontFamily = Typography.labelLarge.fontFamily,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier,
            maxLines = 1
        )

        Spacer(modifier = Modifier.weight(0.1f))

        Text(
            text = TimeLabel(time),
            fontWeight = Typography.labelLarge.fontWeight,
            fontSize = Typography.labelLarge.fontSize,
            fontFamily = Typography.labelLarge.fontFamily,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            maxLines = 1,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = onClick,
            modifier = Modifier
        ) {
            Icon(imageVector = rememberAlarmAdd(), contentDescription = "Time")
        }
    }
}

@Composable
fun DateInput(date: Calendar, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Date:",
            fontWeight = Typography.labelLarge.fontWeight,
            fontSize = Typography.labelLarge.fontSize,
            fontFamily = Typography.labelLarge.fontFamily,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier,
            maxLines = 1
        )

        Spacer(modifier = Modifier.weight(0.11f))

        Text(
            text = "${date.get(Calendar.DAY_OF_MONTH)}.${date.get(Calendar.MONTH) + 1}.${
                date.get(
                    Calendar.YEAR
                )
            }",
            fontWeight = Typography.labelLarge.fontWeight,
            fontSize = Typography.labelLarge.fontSize,
            fontFamily = Typography.labelLarge.fontFamily,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            maxLines = 1,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = onClick,
            modifier = Modifier
        ) {
            Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Date")
        }
    }
}


