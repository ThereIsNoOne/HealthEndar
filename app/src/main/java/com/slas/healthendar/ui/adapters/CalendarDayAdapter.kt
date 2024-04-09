package com.slas.healthendar.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.slas.healthendar.R
import com.slas.healthendar.entity.VisitDto
import com.slas.healthendar.ui.elements.TimeLabel


class CalendarDayAdapter(private val visits: List<VisitDto>) : RecyclerView.Adapter<CalendarDayAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val hourView: TextView
        private val nameView: TextView

        init {
            hourView = view.findViewById(R.id.calendar_hour_view)
            nameView = view.findViewById(R.id.calendar_name_view)
        }

        fun bind(hour: String, name: String) {
            hourView.text = hour
            nameView.text = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.calendar_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return visits.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val visitDto = visits[position]
        holder.bind(TimeLabel(visitDto.time), visitDto.doctor)
    }
}