package com.slas.healthendar.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.slas.healthendar.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todayFragment = TodayFragment()
        val reminderFragment = ReminderFragment()
        val mainFragment = MainFragment()


        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavBar.setOnItemSelectedListener {
            Log.d("Hello", "FROM nav bar")
            when (it.itemId) {
                R.id.remindersView_bottom -> {
                    transfer(reminderFragment)
                    true
                }
                R.id.todayView_bottom -> {
                    transfer(todayFragment)
                    true
                }
                R.id.calendarView_bottom -> {
                    transfer(mainFragment)
                    true
                }

                else -> true
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            insets
        }

    }

    private fun transfer(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }


}