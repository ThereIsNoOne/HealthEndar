package com.slas.healthendar.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.slas.healthendar.R
import com.slas.healthendar.entity.Reminder

class ReminderAdapter(private val reminders: List<Reminder>) : RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameView: TextView
        private val remindMeBtn: MaterialButton
        private val doneBtn: MaterialButton

        init {
            nameView = view.findViewById(R.id.reminder_name_item)
            remindMeBtn = view.findViewById(R.id.reminder_remind_me_button)
            doneBtn = view.findViewById(R.id.reminder_done_button)
        }

        fun bind(name: String) {
            nameView.text = name
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
        holder.bind(reminderDto.title)
    }
}