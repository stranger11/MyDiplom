package com.example.mydiplom.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydiplom.DayResultsAdapter
import com.example.mydiplom.R
import com.example.mydiplom.ui.CaloriesViewModel
import kotlinx.android.synthetic.main.fragment_statistic.view.*


class StatisticFragment : Fragment() {

    private lateinit var cViewModel: CaloriesViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_statistic, container, false)

        val adapter = DayResultsAdapter()
        val recView = root.rec_view
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(requireContext())

        cViewModel = ViewModelProvider(this).get(CaloriesViewModel::class.java)
        cViewModel.readDayResults.observe(viewLifecycleOwner, Observer { dayResult ->
            adapter.setData(dayResult)
        })

        return root
    }

}