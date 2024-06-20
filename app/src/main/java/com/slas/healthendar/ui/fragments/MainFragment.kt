package com.slas.healthendar.ui.fragments

import android.content.Intent
import android.os.Bundle
//import com.applandeo.materialcalendarview.CalendarView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.slas.healthendar.R
import com.slas.healthendar.datastore.DataContext
import com.slas.healthendar.ui.AddVisitActivity
import com.slas.healthendar.ui.MainActivity
import com.slas.healthendar.ui.VisitActivity
import com.slas.healthendar.ui.adapters.CalendarDayAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Calendar


class MainFragment : Fragment() {


    private var selectedDate: List<Int> = DataContext.today()
    private lateinit var adapter: CalendarDayAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Setup adapter
        setAdapter()

        // Fetch data for current day
        getDataSet()

        setButton()
        setRecyclerView()


        setCalendar()

    }

    private fun setCalendar() {
        (activity)?.let { mainActivity ->
            val calendar: CalendarView = mainActivity.findViewById(R.id.calendarView)
            calendar.setOnDateChangeListener { view, year, month, day ->
                Log.d("DateChanged", "$day.$month.$year")
                selectedDate = listOf(day, month, year)
                getDataSet()
            }

        }
    }

    private fun setAdapter() {
        (activity as? MainActivity?)?.let { mainActivity ->
            adapter = CalendarDayAdapter {
                requireActivity().startActivity(Intent(
                    mainActivity, VisitActivity::class.java
                ).also { intent ->
                    intent.putExtra("item", it)
                })
            }
        }
    }

    private fun getDataSet() {
        GlobalScope.launch {
            DataContext.databaseController.getVisit(date = selectedDate, onSuccess = {
                adapter.setVisits(it)
            }, onError = {
                Log.d("Fetched", it)
            })
        }
    }


    private fun setRecyclerView() {
        (activity)?.let { mainActivity ->
            val recyclerView: RecyclerView = mainActivity.findViewById(R.id.calendar_items_view)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(mainActivity)

        }
    }


    private fun setButton() {
        (activity as? MainActivity)?.let {
            val addButton: MaterialButton = it.findViewById(R.id.add_visit_button)
            addButton.setOnClickListener {
                requireActivity().startActivity(Intent(activity, AddVisitActivity::class.java))
            }

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onResume() {
        super.onResume()
        getDataSet()
    }
}