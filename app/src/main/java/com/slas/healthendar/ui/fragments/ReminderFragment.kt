package com.slas.healthendar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.slas.healthendar.R
import com.slas.healthendar.entity.Reminder
import com.slas.healthendar.ui.MainActivity
import com.slas.healthendar.ui.adapters.ReminderAdapter

val items = listOf(
    Reminder("One", 0f),
    Reminder("One", 0f),
    Reminder("One", 0f),
    Reminder("One", 0f),
    Reminder("One", 0f),
    Reminder("One", 0f),
    Reminder("One", 0f),
    Reminder("One", 0f)
)
class ReminderFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        (activity as? MainActivity)?.let {
            val recyclerView: RecyclerView = it.findViewById(R.id.reminders_items_view)
            recyclerView.adapter = ReminderAdapter(items)
            recyclerView.layoutManager = LinearLayoutManager(it)
        }
    }
}