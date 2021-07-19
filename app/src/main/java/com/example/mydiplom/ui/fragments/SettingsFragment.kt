package com.example.mydiplom.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mydiplom.R
import com.example.mydiplom.databinding.FragmentFitBinding
import com.example.mydiplom.databinding.FragmentNormOfDayWaterBinding
import com.example.mydiplom.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    lateinit var preferences: SharedPreferences
    lateinit var sharedPreferences: SharedPreferences
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root

        preferences = requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val name = preferences.getString("NAME", "")
        val height = preferences.getInt("HEIGHT", 0)
        val weight = preferences.getInt("WEIGHT", 0)

        binding.settingsEditName.hint = name
        binding.settingsEditWeight.hint = weight.toString()
        binding.settingsEditHeight.hint = height.toString()

        sharedPreferences = requireActivity().getSharedPreferences(
            "SHARED_PREF",
            Context.MODE_PRIVATE)

        binding.button2.setOnClickListener {
            val name: String = binding.settingsEditName.text.toString()
            val height: Int = binding.settingsEditHeight.text.toString().toInt()
            val weight: Int = binding.settingsEditWeight.text.toString().toInt()
            //val checked: Boolean = checkBox.isChecked

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("NAME", name)
            editor.putInt("HEIGHT", height)
            editor.putInt("WEIGHT", weight)
            //editor.putBoolean("CHECKBOX", checked)
            editor.apply()
            Toast.makeText(requireContext(), "Information saved", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_settingsFragment_to_fitFragment)
        }

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}