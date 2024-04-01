package com.slas.healthendar.ui.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.slas.healthendar.ui.AddVisitActivity
import java.io.Serializable

fun <T: ComponentActivity> newActivity(context: Context, newActivity: Class<T>) {
    context.startActivity(Intent(context, newActivity))
}

fun <T: ComponentActivity, B: Serializable> newActivity(
    context: Context,
    newActivity: Class<T>,
    extra: B,
    extraTitle: String = "item"
) {
    val intent = Intent(context, newActivity)
    intent.putExtra(extraTitle, extra)
    context.startActivity(intent)
}