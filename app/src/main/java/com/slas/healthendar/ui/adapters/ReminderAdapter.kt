package com.slas.healthendar.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.slas.healthendar.R
import com.slas.healthendar.datastore.DataContext
import com.slas.healthendar.entity.Reminder
import com.slas.healthendar.entity.Visit
import com.slas.healthendar.ui.elements.cancel
import com.slas.healthendar.ui.elements.createNotification
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.stream.Collectors

class ReminderAdapter(private var visits: MutableList<Visit>) :
    RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {

    private val currentTime = Calendar.getInstance().timeInMillis
    private val reminders: MutableList<Reminder> = visits.flatMap { it.reminders }
        .filter { it.active && it.notificationTimestamp > currentTime }.toList().stream()
        .collect(Collectors.toList())


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val nameView: TextView = view.findViewById(R.id.reminder_name_item)
        private val remindMeBtn: MaterialButton = view.findViewById(R.id.reminder_remind_me_button)
        private val doneBtn: MaterialButton = view.findViewById(R.id.reminder_done_button)

        fun bind(reminder: Reminder, action: (Context) -> Unit, update: (Context) -> Unit) {
            nameView.text = reminder.title
            doneBtn.setOnClickListener { _ ->
                cancel(reminder, view.context)
                reminder.active = false
                action(view.context)
            }
            remindMeBtn.setOnClickListener {
                cancel(reminder, view.context)
                createNotification(reminder, view.context)
                Toast.makeText(view.context, "Reminder delayed 15 min", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.extended_reminder_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reminders.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reminderDto = reminders[position]

        holder.bind(reminderDto, action = { context ->
            visits.forEach {
                Log.d("Items", "Item $it")
            }

            reminders.removeAt(position)
            visits.forEach {
                if (it.reminders.contains(reminderDto)) {
                    GlobalScope.launch {
                        DataContext.databaseController.addReminder(
                            it,
                            onSuccess = {
//                                Toast.makeText(context, "Marked as done", Toast.LENGTH_SHORT).show()
                            },
                            onError = { msg ->
//                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            })
                    }
                }
            }
            notifyDataSetChanged()
            Log.d("DELETED", "Item deleted $position")

            visits.forEach {
                Log.d("Items", "Item $it")
            }
        }, update = {context ->
            visits.forEach {
                if (it.reminders.contains(reminderDto)) {
                    GlobalScope.launch {
                        DataContext.databaseController.addReminder(
                            it,
                            onSuccess = {
//                                Toast.makeText(context, "Marked as done", Toast.LENGTH_SHORT).show()
                            },
                            onError = { msg ->
//                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            })
                    }
                }
            }
        })
    }
}