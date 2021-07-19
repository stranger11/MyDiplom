package com.example.mydiplom.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydiplom.R
import com.example.mydiplom.adapters.RunAdapter
import com.example.mydiplom.databinding.FragmentAddToStatisticBinding
import com.example.mydiplom.databinding.FragmentAllRunsBinding
import com.example.mydiplom.ui.CaloriesViewModel
import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.android.synthetic.main.fragment_all_runs.*


@AndroidEntryPoint
class AllRunsFragment : Fragment() {

    private var _binding: FragmentAllRunsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CaloriesViewModel by viewModels()

    private lateinit var runAdapter: RunAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentAllRunsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.runsSortedByDate.observe(viewLifecycleOwner, Observer {
            runAdapter.submitList(it)
        })
    }

    private fun setupRecyclerView() = binding.rvRuns.apply {
        runAdapter = RunAdapter()
        adapter = runAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }
}