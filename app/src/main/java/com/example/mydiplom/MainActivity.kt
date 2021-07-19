package com.example.mydiplom

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.mydiplom.util.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import com.example.mydiplom.util.Constants.NIGHT_MODE
import com.example.mydiplom.util.Constants.SAVED_STATE
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.ui.setupActionBarWithNavController

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (loadState()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.MyThemeNight)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            setTheme(R.style.MyThemeDay)
        }
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.fragment))
        navigateToTrackingFragmentIfNeeded(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }

    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?) {
        if(intent?.action == ACTION_SHOW_TRACKING_FRAGMENT) {
            val fragment : NavHostFragment = findViewById(R.id.fragment)
            fragment.findNavController().navigate(R.id.action_global_trackingFragment)
        }
    }

    private fun loadState(): Boolean {
        val sharedPreferences: SharedPreferences =
                this.getSharedPreferences(SAVED_STATE, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(NIGHT_MODE, false)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}