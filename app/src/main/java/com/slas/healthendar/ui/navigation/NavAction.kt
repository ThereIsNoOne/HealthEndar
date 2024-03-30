package com.slas.healthendar.ui.navigation

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import com.slas.healthendar.ui.AddVisitActivity

fun <T: ComponentActivity> newActivity(context: Context, newActivity: Class<T>) {
    context.startActivity(Intent(context, newActivity))
}