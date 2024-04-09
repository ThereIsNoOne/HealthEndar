package com.slas.healthendar.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.navigation.NavigationBarView
import com.slas.healthendar.R

class ReminderActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()




        setContentView(R.layout.reminders_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        NavigationBarView.OnItemSelectedListener {
            when (it.itemId) {
                R.id.calendarView_bottom -> {
                    true
                }

                R.id.todayView_bottom -> {
                    true
                }

                R.id.remindersView_bottom -> {
                    true
                }

                else -> {
                    true
                }
            }
        }
    }
}