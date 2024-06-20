package com.slas.healthendar.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.slas.healthendar.R
import com.slas.healthendar.datastore.NotificationContext
import com.slas.healthendar.ui.fragments.MainFragment
import com.slas.healthendar.ui.fragments.ReminderFragment
import com.slas.healthendar.ui.fragments.TodayFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NotificationContext.CHANNEL_ID,
                NotificationContext.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(notificationChannel)
        }


        setContentView(R.layout.activity_main)
        val userId = intent.getStringExtra("uid")

        if (userId != null) {
            Log.d("userId", userId)
        }
        setNavBar()

        val topBar: MaterialToolbar = findViewById(R.id.toolbar)

        topBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.login_button -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    true
                }

                else -> true
            }
        }


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