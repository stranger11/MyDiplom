package com.example.mydiplom.ui.fragments


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mydiplom.R
import kotlinx.android.synthetic.main.fragment_hello.*


class HelloFragment : Fragment() {

    private lateinit var textHello: TextView
    private lateinit var editHeight: EditText
    private lateinit var editWeight: EditText
    private lateinit var buttonContinue: Button

    lateinit var sharedPreferences: SharedPreferences
    var isRemember = false

    private lateinit var fitFragment: FitFragment

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_hello, container, false)
        fitFragment = FitFragment()

        textHello = root.findViewById(R.id.text_hello)
        editHeight = root.findViewById(R.id.edittext_height)
        editWeight = root.findViewById(R.id.edittext_weight)
        buttonContinue = root.findViewById(R.id.button_continue)

        sharedPreferences = requireActivity().getSharedPreferences(
                "SHARED_PREF",
                Context.MODE_PRIVATE)
        isRemember = sharedPreferences.getBoolean(
                "CHECKBOX",
                false)

        if (isRemember) {

            //replaceFragment(fitFragment)
            findNavController().navigate(R.id.action_helloFragment_to_fitFragment)
        }

        buttonContinue.setOnClickListener {

            val height: Int = editHeight.text.toString().toInt()
            val weight: Int = editWeight.text.toString().toInt()
            val checked: Boolean = checkBox.isChecked

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("HEIGHT", height)
            editor.putInt("WEIGHT", weight)
            editor.putBoolean("CHECKBOX", checked)
            editor.apply()

            Toast.makeText(requireContext(), "Information saved", Toast.LENGTH_SHORT).show()

            //replaceFragment(fitFragment)
            findNavController().navigate(R.id.action_helloFragment_to_fitFragment)

        }
        return root
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, fragment)
        transaction.commit()
    }
}