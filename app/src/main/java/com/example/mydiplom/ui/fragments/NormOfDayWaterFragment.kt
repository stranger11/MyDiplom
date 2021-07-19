package com.example.mydiplom.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.example.mydiplom.R
import com.example.mydiplom.databinding.FragmentNormOfDayWaterBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
//import kotlinx.android.synthetic.main.fragment_norm_of_day_water.*
//import kotlinx.android.synthetic.main.fragment_norm_of_day_water.view.*
import me.itangqi.waveloadingview.WaveLoadingView
import kotlin.math.max


class NormOfDayWaterFragment : Fragment() {


    private var _binding: FragmentNormOfDayWaterBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<NormOfDayWaterFragmentArgs>()
    private lateinit var mWaveLoadingView: WaveLoadingView

    var normWaterForTheDay: Double = 0.0
    lateinit var preferences: SharedPreferences

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //val root = inflater.inflate(R.layout.fragment_norm_of_day_water, container, false)
        _binding = FragmentNormOfDayWaterBinding.inflate(inflater, container, false)
        val view = binding.root

        preferences = requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val weight = preferences.getInt("WEIGHT", 0)
        normWaterForTheDay = 33.0 * weight

        mWaveLoadingView = view.findViewById(R.id.waveLoadingView)
        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE)
        mWaveLoadingView.topTitle = ""
        mWaveLoadingView.centerTitleColor = Color.rgb(80,240,144)
        mWaveLoadingView.bottomTitleSize = 18F
        mWaveLoadingView.borderWidth = 10F
        mWaveLoadingView.setAmplitudeRatio(60)
        mWaveLoadingView.waveColor = Color.rgb(38,121,188)
        mWaveLoadingView.borderColor = Color.rgb(38,121,188)
        mWaveLoadingView.setTopTitleStrokeColor(Color.rgb(38,121,188))
        mWaveLoadingView.setTopTitleStrokeWidth(10F)
        mWaveLoadingView.setAnimDuration(2000)
        mWaveLoadingView.pauseAnimation()
        mWaveLoadingView.resumeAnimation()
        mWaveLoadingView.cancelAnimation()
        mWaveLoadingView.startAnimation()

        binding.textDrunkWater.text = args.daywater.toString()
        binding.textNormOfDayWWater2.text = normWaterForTheDay.toInt().toString()
        val a = binding.textDrunkWater.text.toString().toInt()
        val b = binding.textNormOfDayWWater2.text.toString().toInt()

        val percentage = (a * 100.0) / b

        mWaveLoadingView.progressValue = percentage.toInt()
        mWaveLoadingView.centerTitle = "${percentage.toInt()} %"

        if (percentage > 100) {
            mWaveLoadingView.centerTitle = "100 %"
        }

        return view
    }
}