package com.slas.healthendar.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.slas.healthendar.R
import com.slas.healthendar.datastore.DataContext
import com.slas.healthendar.entity.Reminder
import com.slas.healthendar.entity.Visit
import com.slas.healthendar.ui.MainActivity
import com.slas.healthendar.ui.adapters.ReminderAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.stream.Collectors


class ReminderFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch {
            DataContext.databaseController.getReminders(
                onSuccess = {
                    val currentTime = Calendar.getInstance().timeInMillis
                    setRecyclerView(
                        it.stream().collect(Collectors.toList())
//                        it.stream()
//                            .filter {item -> item.active && item.notificationTimestamp > currentTime}
//                            .collect(Collectors.toList())
                    )
                },
                onError = {
                    Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
                    Log.d("Error", it)
                }
            )
        }
//        setRecyclerView(items)
    }

    private fun setRecyclerView(items: MutableList<Visit>) {
        (activity as? MainActivity)?.let {
            val recyclerView: RecyclerView = it.findViewById(R.id.reminders_items_view)
            recyclerView.adapter = ReminderAdapter(items)
            recyclerView.layoutManager = LinearLayoutManager(it)
        }
    }
}