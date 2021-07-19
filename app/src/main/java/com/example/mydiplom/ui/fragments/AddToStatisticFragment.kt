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
import com.example.mydiplom.data.DayResults
import com.example.mydiplom.databinding.FragmentAddToStatisticBinding


class AddToStatisticFragment : Fragment() {

    private var _binding: FragmentAddToStatisticBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<AddToStatisticFragmentArgs>()
    private lateinit var viewModel: CaloriesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentAddToStatisticBinding.inflate(inflater, container, false)
        val view = binding.root
        //val root = inflater.inflate(R.layout.fragment_add_to_statistic, container, false)

        viewModel = ViewModelProvider(this).get(CaloriesViewModel::class.java)

        viewModel.readCalorie.observe(viewLifecycleOwner, Observer {})
        viewModel.readWater.observe(viewLifecycleOwner, Observer {})
        viewModel.readPushUps.observe(viewLifecycleOwner, Observer {})
        viewModel.readSquats.observe(viewLifecycleOwner, Observer {})
        viewModel.readPress.observe(viewLifecycleOwner, Observer {})
        viewModel.readRun.observe(viewLifecycleOwner, Observer {})

        binding.fromFitDate.text = args.date
        binding.fromFitWater.text = args.water.toString()
        binding.fromFitCalories.text = args.calories.toString()
        binding.fromFitPushups.text = args.pushUps.toString()
        binding.fromFitSquats.text = args.squats.toString()
        binding.fromFitPress.text = args.press.toString()
        binding.fromFitRun.text = args.runKm.toString()

        binding.button.setOnClickListener {
            addToStatistic()
            updateDbToZero()
        }

        return view
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
        val dateTime = binding.fromFitDate.text.toString()
        val water = binding.fromFitWater.text.toString().toInt()
        val calories = binding.fromFitCalories.text.toString().toDouble()
        val pushUps = binding.fromFitPushups.text.toString().toInt()
        val squats = binding.fromFitSquats.text.toString().toInt()
        val press = binding.fromFitPress.text.toString().toInt()
        val run = binding.fromFitRun.text.toString().toInt()

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}