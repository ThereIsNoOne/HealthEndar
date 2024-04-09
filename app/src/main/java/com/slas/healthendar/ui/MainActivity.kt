package com.slas.healthendar.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.slas.healthendar.R
import com.slas.healthendar.ui.fragments.MainFragment
import com.slas.healthendar.ui.fragments.ReminderFragment
import com.slas.healthendar.ui.fragments.TodayFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setNavBar()



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.add_appointment)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            insets
        }

    }


    private fun setNavBar() {
        val todayFragment = TodayFragment()
        val reminderFragment = ReminderFragment()
        val mainFragment = MainFragment()


        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavBar.setOnItemSelectedListener {
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
    }

    private fun transfer(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }


}