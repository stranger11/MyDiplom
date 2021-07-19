package com.example.mydiplom.ui.fragments

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.mydiplom.ui.fragments.FitFragmentDirections
import com.example.mydiplom.R
import com.example.mydiplom.data.DayResults
import com.example.mydiplom.data.Run
import com.example.mydiplom.databinding.FragmentFitBinding
import com.example.mydiplom.ui.CaloriesViewModel
import com.example.mydiplom.util.Constants.NIGHT_MODE
import com.example.mydiplom.util.Constants.REQUEST_CODE_LOCATION_PERMISSION
import com.example.mydiplom.util.Constants.SAVED_STATE
import com.example.mydiplom.util.TrackingUtility
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


@AndroidEntryPoint
class FitFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    private var _binding: FragmentFitBinding? = null
    private val binding get() = _binding!!

    private val calViewModel: CaloriesViewModel by viewModels()
    private lateinit var lottiePress: LottieAnimationView
    var normWaterForTheDay: Double = 0.0

    lateinit var preferences: SharedPreferences


    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFitBinding.inflate(inflater, container, false)
        val view = binding.root
        initSwitchTheme(view)
        binding.dateText.text = TrackingUtility.getCurrentDate()

        calViewModel.readCalorie.observe(viewLifecycleOwner, Observer {
            it?.let {
                YoYo.with(Techniques.SlideInDown)
                    .duration(500)
                    .playOn(binding.textCalories)
                binding.textCalories.text = it.toInt().toString()
            }
        })

        calViewModel.readWater.observe(viewLifecycleOwner, Observer {
            it?.let {
                YoYo.with(Techniques.SlideInLeft)
                        .duration(500)
                        .playOn(binding.textItogWater)
                binding.textItogWater.text = it.toString()
            }
        })

        calViewModel.readPushUps.observe(viewLifecycleOwner, Observer {
            it?.let {
                YoYo.with(Techniques.SlideInLeft)
                        .duration(500)
                        .playOn(binding.textItogPushUp)
                binding.textItogPushUp.text = it.toString()
            }
        })

        calViewModel.readSquats.observe(viewLifecycleOwner, Observer {
            it?.let {
                YoYo.with(Techniques.SlideInLeft)
                        .duration(500)
                        .playOn(binding.textItogSquat)
                binding.textItogSquat.text = it.toString()
            }
        })

        calViewModel.readPress.observe(viewLifecycleOwner, Observer {
            it?.let {
                YoYo.with(Techniques.SlideInLeft)
                        .duration(500)
                        .playOn(binding.textItogPress)
                binding.textItogPress.text = it.toString()
            }
        })

        calViewModel.readRun.observe(viewLifecycleOwner, Observer {
            it?.let {
                YoYo.with(Techniques.SlideInLeft)
                        .duration(500)
                        .playOn(binding.textItogRun)
                binding.textItogRun.text = it.toString()
            }
        })

        preferences = requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val weight = preferences.getInt("WEIGHT", 0)
        normWaterForTheDay = 33.0 * weight

        binding.textNormOfDayWWater.text = normWaterForTheDay.toString()

        binding.imageWater.setOnClickListener {
            val a = binding.textItogWater.text.toString().toInt()
            val action = FitFragmentDirections.actionFitFragmentToNormOfDayWaterFragment(a)
            findNavController().navigate(action)
        }

        binding.textGlassOfWater.setOnClickListener {
            calViewModel.updateWater(250)
        }

        binding.textCupOfCoffee.setOnClickListener {
            calViewModel.updateWater(150)
        }

        binding.textCupOfTea.setOnClickListener {
            calViewModel.updateWater(150)
        }

        //PushUps

        binding.text20PushUps.setOnClickListener {
            calViewModel.updateCalories(18)
            calViewModel.updatePushUps(20)
        }

        binding.text30PushUps.setOnClickListener {
            calViewModel.updateCalories(27)
            calViewModel.updatePushUps(30)
        }

        binding.text50PushUps.setOnClickListener {
            calViewModel.updateCalories(45)
            calViewModel.updatePushUps(50)
        }

        binding.textMyPushUps.setOnClickListener {
            calViewModel.updateCalories(binding.textMyPushUps.text.toString().toInt())
            calViewModel.updatePushUps(binding.textMyPushUps.text.toString().toInt())
        }

        //Squats

        binding.text20Squats.setOnClickListener {
            calViewModel.updateCalories(9)
            calViewModel.updateSquats(20)
        }

        binding.text30Squats.setOnClickListener {
            calViewModel.updateCalories(13)
            calViewModel.updateSquats(30)
        }

        binding.text50Squats.setOnClickListener {
            calViewModel.updateCalories(22)
            calViewModel.updateSquats(50)
        }

        binding.textMySquats.setOnClickListener {
            val a  = binding.textMySquats.text.toString().toInt() * 0.43
            calViewModel.updateCalories(a.toInt())
            calViewModel.updateSquats(binding.textMySquats.text.toString().toInt())
        }

        //Press

        binding.text30Press.setOnClickListener {
            calViewModel.updateCalories(8)
            calViewModel.updatePress(30)
        }

        binding.text50Press.setOnClickListener {
            calViewModel.updateCalories(13)
            calViewModel.updatePress(50)
        }

        binding.text100Press.setOnClickListener {
            calViewModel.updateCalories(26)
            calViewModel.updatePress(100)
        }

        binding.textMyPress.setOnClickListener {
            val a = binding.textMyPress.text.toString().toInt() * 0.26
            calViewModel.updateCalories(a.toInt())
            calViewModel.updatePress(binding.textMyPress.text.toString().toInt())
        }

        binding.buttonTotalDay.setOnClickListener {

            val date = binding.dateText.text.toString()
            val dayWater = binding.textItogWater.text.toString().toInt()
            val dayCalories = binding.textCalories.text.toString().toDouble()
            val dayPushUps = binding.textItogPushUp.text.toString().toInt()
            val daySquats = binding.textItogSquat.text.toString().toInt()
            val dayPress = binding.textItogPress.text.toString().toInt()
            val dayRun = binding.textItogRun.text.toString().toInt()

            val action = FitFragmentDirections.actionFitFragmentToAddToStatisticFragment(
                date,
                dayCalories.toInt(),
                dayWater,
                dayPushUps,
                daySquats,
                dayPress,
                dayRun
            )

            findNavController().navigate(action)

        }

        binding.imageSettings.setOnClickListener {
            findNavController().navigate(R.id.action_fitFragment_to_helloFragment)
        }


        binding.imageStatistic.setOnClickListener {
            findNavController().navigate(R.id.action_fitFragment_to_statisticFragment)
        }

        binding.textRun.setOnClickListener {
            findNavController().navigate(R.id.action_fitFragment_to_trackingFragment)
        }

        binding.imageView7.setOnClickListener {
            findNavController().navigate(R.id.action_fitFragment_to_allRunsFragment)
        }

        binding.imagePress.setOnClickListener {
            showPressAnimation()
        }

        binding.imageSettings.setOnClickListener {
            findNavController().navigate(R.id.action_fitFragment_to_settingsFragment)
        }

       return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
    }


    private fun showPressAnimation() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.water_level_dialog_window)
        dialog.window?.setBackgroundDrawableResource(R.color.black)
        lottiePress = dialog.findViewById(R.id.lottie_press)
        lottiePress.speed = 3f
        lottiePress.playAnimation()

        dialog.show()

    }

    private fun requestPermissions() {
        if (TrackingUtility.hasLocationPermissions(requireContext())) {
            return
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app.",
                REQUEST_CODE_LOCATION_PERMISSION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app.",
                REQUEST_CODE_LOCATION_PERMISSION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }
    }


    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestPermissions()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    fun initSwitchTheme(v: View) {
        if (loadState()) {
            binding.themeSwitch.isChecked = true
        }
        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                saveState(true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                saveState(false)
            }
        }
    }

    private fun saveState(state: Boolean) {
        val sharedPreferences: SharedPreferences =
                requireActivity().getSharedPreferences(SAVED_STATE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(NIGHT_MODE, state)
        editor.apply()
    }

    private fun loadState(): Boolean {
        val sharedPreferences: SharedPreferences =
                requireActivity().getSharedPreferences(SAVED_STATE, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(NIGHT_MODE, false)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}