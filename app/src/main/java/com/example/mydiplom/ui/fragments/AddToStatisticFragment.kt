package com.example.mydiplom.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mydiplom.ui.CaloriesViewModel
import com.example.mydiplom.R
import com.example.mydiplom.ui.fragments.AddToStatisticFragmentArgs
import com.example.mydiplom.data.DayResults
import kotlinx.android.synthetic.main.fragment_add_to_statistic.*
import kotlinx.android.synthetic.main.fragment_add_to_statistic.view.*


class AddToStatisticFragment : Fragment() {


    private val args by navArgs<AddToStatisticFragmentArgs>()
    private lateinit var viewModel: CaloriesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_add_to_statistic, container, false)

        viewModel = ViewModelProvider(this).get(CaloriesViewModel::class.java)

        viewModel.readCalorie.observe(viewLifecycleOwner, Observer {})
        viewModel.readWater.observe(viewLifecycleOwner, Observer {})
        viewModel.readPushUps.observe(viewLifecycleOwner, Observer {})
        viewModel.readSquats.observe(viewLifecycleOwner, Observer {})
        viewModel.readPress.observe(viewLifecycleOwner, Observer {})
        viewModel.readRun.observe(viewLifecycleOwner, Observer {})

        root.from_fit_date.text = args.date
        root.from_fit_water.text = args.water.toString()
        root.from_fit_calories.text = args.calories.toString()
        root.from_fit_pushups.text = args.pushUps.toString()
        root.from_fit_squats.text = args.squats.toString()
        root.from_fit_press.text = args.press.toString()
        root.from_fit_run.text = args.runKm.toString()

        root.button.setOnClickListener {
            addToStatistic()
            updateDbToZero()
        }


        return root
    }

    private fun updateDbToZero() {
        viewModel.updateCaloriesToZero()
        viewModel.updateWaterToZero()
        viewModel.updatePushUpsToZero()
        viewModel.updateSquatsToZero()
        viewModel.updatePressToZero()
        viewModel.updateRunToZero()
        viewModel.updateCaloriesToZero()
    }


    private fun addToStatistic() {
        val dateTime = from_fit_date.text.toString()
        val water = from_fit_water.text.toString().toInt()
        val calories = from_fit_calories.text.toString().toDouble()
        val pushUps = from_fit_pushups.text.toString().toInt()
        val squats = from_fit_squats.text.toString().toInt()
        val press = from_fit_press.text.toString().toInt()
        val run = from_fit_run.text.toString().toInt()

        val dayResults = DayResults(
            0,
            dateTime,
            water,
            calories,
            pushUps,
            squats,
            press,
            run
        )
        viewModel.addDayResults(dayResults)
        Toast.makeText(requireContext(), "Was added", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_addToStatisticFragment_to_fitFragment)
    }

}