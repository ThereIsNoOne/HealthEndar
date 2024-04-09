package com.slas.healthendar.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.slas.healthendar.R
import com.slas.healthendar.ui.MainActivity
import com.slas.healthendar.ui.VisitActivity
import com.slas.healthendar.ui.adapters.TodayAdapter

class TodayFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        (activity as? MainActivity)?.let {mainActivity ->
            val recyclerView: RecyclerView = mainActivity.findViewById(R.id.today_items_view)
            recyclerView.adapter = TodayAdapter(mockData) {
                mainActivity.startActivity(
                    Intent(
                        mainActivity,
                        VisitActivity::class.java
                    ).also {intent ->
                        intent.putExtra("item", it)
                    }
                )
            }
            recyclerView.layoutManager = LinearLayoutManager(mainActivity)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_today, container, false)
    }

}