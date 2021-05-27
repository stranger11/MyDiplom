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
import com.example.mydiplom.ui.CaloriesViewModel
import com.example.mydiplom.util.Constants.REQUEST_CODE_LOCATION_PERMISSION
import com.example.mydiplom.util.TrackingUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_fit.*
import kotlinx.android.synthetic.main.fragment_fit.view.*
import me.itangqi.waveloadingview.WaveLoadingView
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


@AndroidEntryPoint
class FitFragment : Fragment(), EasyPermissions.PermissionCallbacks {


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
        val root = inflater.inflate(R.layout.fragment_fit, container, false)

        root.date_text.text = TrackingUtility.getCurrentDate()

        //lottiePress = root.findViewById(R.id.lottie_press)

        calViewModel.readCalorie.observe(viewLifecycleOwner, Observer {
            it?.let {
                YoYo.with(Techniques.SlideInDown)
                    .duration(500)
                    .playOn(textCalories)
                textCalories.text = it.toInt().toString()
            }
        })

        calViewModel.readWater.observe(viewLifecycleOwner, Observer {
            it?.let {
                YoYo.with(Techniques.SlideInLeft)
                        .duration(500)
                        .playOn(textItogWater)
                textItogWater.text = it.toString()
            }
        })

        calViewModel.readPushUps.observe(viewLifecycleOwner, Observer {
            it?.let {
                YoYo.with(Techniques.SlideInLeft)
                        .duration(500)
                        .playOn(textItogPushUp)
                textItogPushUp.text = it.toString()
            }
        })

        calViewModel.readSquats.observe(viewLifecycleOwner, Observer {
            it?.let {
                YoYo.with(Techniques.SlideInLeft)
                        .duration(500)
                        .playOn(textItogSquat)
                textItogSquat.text = it.toString()
            }
        })

        calViewModel.readPress.observe(viewLifecycleOwner, Observer {
            it?.let {
                YoYo.with(Techniques.SlideInLeft)
                        .duration(500)
                        .playOn(textItogPress)
                textItogPress.text = it.toString()
            }
        })

        calViewModel.readRun.observe(viewLifecycleOwner, Observer {
            it?.let {
                YoYo.with(Techniques.SlideInLeft)
                        .duration(500)
                        .playOn(textItogRun)
                textItogRun.text = it.toString()
            }
        })

        preferences = requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val weight = preferences.getInt("WEIGHT", 0)
        normWaterForTheDay = 33.0 * weight

        root.textNormOfDayWWater.text = normWaterForTheDay.toString()

        root.image_water.setOnClickListener {
            //showWaterLevel()
            val a = textItogWater.text.toString().toInt()
            val action = FitFragmentDirections.actionFitFragmentToNormOfDayWaterFragment(a)
            findNavController().navigate(action)
        }

        root.textGlassOfWater.setOnClickListener {
            calViewModel.updateWater(250)
        }

        root.textCupOfCoffee.setOnClickListener {
            calViewModel.updateWater(150)
        }

        root.textCupOfTea.setOnClickListener {
            calViewModel.updateWater(150)
        }

        //PushUps

        root.text20PushUps.setOnClickListener {
            calViewModel.updateCalories(18)
            calViewModel.updatePushUps(20)
        }

        root.text30PushUps.setOnClickListener {
            calViewModel.updateCalories(27)
            calViewModel.updatePushUps(30)
        }

        root.text50PushUps.setOnClickListener {
            calViewModel.updateCalories(45)
            calViewModel.updatePushUps(50)
        }

        root.textMyPushUps.setOnClickListener {
            calViewModel.updateCalories(textMyPushUps.text.toString().toInt())
            calViewModel.updatePushUps(textMyPushUps.text.toString().toInt())
        }

        //Squats

        root.text20Squats.setOnClickListener {
            calViewModel.updateCalories(9)
            calViewModel.updateSquats(20)
        }

        root.text30Squats.setOnClickListener {
            calViewModel.updateCalories(13)
            calViewModel.updateSquats(30)
        }

        root.text50Squats.setOnClickListener {
            calViewModel.updateCalories(22)
            calViewModel.updateSquats(50)
        }

        root.textMySquats.setOnClickListener {
            val a  = textMySquats.text.toString().toInt() * 0.43
            calViewModel.updateCalories(a.toInt())
            calViewModel.updateSquats(textMySquats.text.toString().toInt())
        }

        //Press

        root.text30Press.setOnClickListener {
            calViewModel.updateCalories(8)
            calViewModel.updatePress(30)
        }

        root.text50Press.setOnClickListener {
            calViewModel.updateCalories(13)
            calViewModel.updatePress(50)
        }

        root.text100Press.setOnClickListener {
            calViewModel.updateCalories(26)
            calViewModel.updatePress(100)
        }

        root.textMyPress.setOnClickListener {
            val a = textMyPress.text.toString().toInt() * 0.26
            calViewModel.updateCalories(a.toInt())
            calViewModel.updatePress(textMyPress.text.toString().toInt())
        }

        root.button_total_day.setOnClickListener {

            val date = date_text.text.toString()
            val dayWater = textItogWater.text.toString().toInt()
            val dayCalories = textCalories.text.toString().toDouble()
            val dayPushUps = textItogPushUp.text.toString().toInt()
            val daySquats = textItogSquat.text.toString().toInt()
            val dayPress = textItogPress.text.toString().toInt()
            val dayRun = textItogRun.text.toString().toInt()

            val action = FitFragmentDirections.actionFitFragmentToAddToStatisticFragment(
                date,
                dayWater,
                dayCalories.toInt(),
                dayPushUps,
                daySquats,
                dayPress,
                dayRun
            )

            findNavController().navigate(action)

        }

        root.image_settings.setOnClickListener {
            findNavController().navigate(R.id.action_fitFragment_to_helloFragment)
        }


        root.image_statistic.setOnClickListener {
            findNavController().navigate(R.id.action_fitFragment_to_statisticFragment)
        }

        root.textRun.setOnClickListener {
            findNavController().navigate(R.id.action_fitFragment_to_trackingFragment)
        }

        root.imageView7.setOnClickListener {
            findNavController().navigate(R.id.action_fitFragment_to_allRunsFragment)
        }

        root.image_press.setOnClickListener {
            showPressAnimation()
        }

       return root
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
}