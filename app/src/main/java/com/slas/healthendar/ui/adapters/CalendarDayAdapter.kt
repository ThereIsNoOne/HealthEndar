package com.slas.healthendar.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.slas.healthendar.R
import com.slas.healthendar.entity.Visit
import com.slas.healthendar.ui.elements.TimeLabel


class CalendarDayAdapter(private val onClick: (Visit) -> Unit) : RecyclerView.Adapter<CalendarDayAdapter.ViewHolder>() {

    private var visits: Array<Visit> = arrayOf()

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

    @SuppressLint("NotifyDataSetChanged")   // Whole data set changes
    fun setVisits(data: Array<Visit>) {
        this.visits = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return visits.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val visitDto = visits[position]
        holder.bind(TimeLabel(visitDto.time), visitDto.doctor)
        holder.itemView.setOnClickListener { onClick(visitDto) }
    }
}