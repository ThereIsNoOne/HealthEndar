package com.slas.healthendar.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.slas.healthendar.R
import com.slas.healthendar.entity.VisitDto
import com.slas.healthendar.ui.AddVisitActivity
import com.slas.healthendar.ui.MainActivity
import com.slas.healthendar.ui.adapters.CalendarDayAdapter

val mockData = listOf(
    VisitDto(
        "dr. House",
        "Neuro",
        9 * 60,
        arrayOf(2024, 4, 24),
        "Wroclaw",
        "123456789",
        "dr.house@house.pl",
    ),
    VisitDto(
        "dr. House",
        "Neuro",
        9 * 60,
        arrayOf(2024, 4, 24),
        "Wroclaw",
        mail = "dr.house@house.pl"
    ),
    VisitDto("dr. House", "Neuro", 9 * 60, arrayOf(2024, 4, 24), "Wroclaw", "123456789"),
    VisitDto(
        "dr. House",
        "Neuro",
        9 * 60,
        arrayOf(2024, 4, 25),
        "Wroclaw",
        "123456789",
        "dr.house@house.pl"
    ),
    VisitDto(
        "dr. House",
        "Neuro",
        9 * 60,
        arrayOf(2024, 4, 26),
        "Wroclaw",
        "123456789",
        "dr.house@house.pl"
    )
)

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    private lateinit var viewOnCreate: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.viewOnCreate = view;
        setButton()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        (activity as? MainActivity)?.let {
            val recyclerView: RecyclerView = it.findViewById(R.id.calendar_items_view)
            recyclerView.adapter = CalendarDayAdapter(mockData)
            recyclerView.layoutManager = LinearLayoutManager(it)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)

    }
}