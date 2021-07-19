package com.example.mydiplom.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiplom.adapters.DayResultsAdapter
import com.example.mydiplom.R
import com.example.mydiplom.ui.CaloriesViewModel
//import kotlinx.android.synthetic.main.fragment_statistic.view.*


class StatisticFragment : Fragment() {

    private lateinit var cViewModel: CaloriesViewModel
    private lateinit var recView: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_statistic, container, false)
        recView = root.findViewById(R.id.rec_view)
        val adapter = DayResultsAdapter()

        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(requireContext())

        cViewModel = ViewModelProvider(this).get(CaloriesViewModel::class.java)
        cViewModel.readDayResults.observe(viewLifecycleOwner, Observer { dayResult ->
            adapter.setData(dayResult)
        })

        return root
    }

}