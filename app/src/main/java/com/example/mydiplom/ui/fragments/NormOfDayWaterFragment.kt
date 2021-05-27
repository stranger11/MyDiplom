package com.example.mydiplom.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.example.mydiplom.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_norm_of_day_water.*
import kotlinx.android.synthetic.main.fragment_norm_of_day_water.view.*
import me.itangqi.waveloadingview.WaveLoadingView
import kotlin.math.max


class NormOfDayWaterFragment : Fragment() {


    private val args by navArgs<NormOfDayWaterFragmentArgs>()
    private lateinit var mWaveLoadingView: WaveLoadingView


    var normWaterForTheDay: Double = 0.0

    lateinit var preferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_norm_of_day_water, container, false)

        preferences = requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val weight = preferences.getInt("WEIGHT", 0)
        normWaterForTheDay = 33.0 * weight

        mWaveLoadingView = root.findViewById(R.id.waveLoadingView)
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



        root.textDrunkWater.text = args.daywater.toString()
        root.textNormOfDayWWater2.text = normWaterForTheDay.toInt().toString()
        val a = root.textDrunkWater.text.toString().toInt()
        val b = root.textNormOfDayWWater2.text.toString().toInt()

        val percentage = (a *100) / b

        mWaveLoadingView.progressValue = percentage
        mWaveLoadingView.centerTitle = "$percentage %"

        if (percentage > 100) {
            mWaveLoadingView.centerTitle = "100 %"
        }


        val entries = ArrayList<Entry>()
        val dateArray = ArrayList<String>()

        if (!entries.isEmpty()) {
            root.chart.description.isEnabled = false
            root.chart.animateY(1000, Easing.Linear)
            root.chart.viewPortHandler.setMaximumScaleX(1.5f)
            root.chart.xAxis.setDrawGridLines(false)
            root.chart.xAxis.position = XAxis.XAxisPosition.TOP
            root.chart.xAxis.isGranularityEnabled = true
            root.chart.legend.isEnabled = false
            root.chart.fitScreen()
            root.chart.isAutoScaleMinMaxEnabled = true
            root.chart.scaleX = 1f
            root.chart.setPinchZoom(true)
            root.chart.isScaleXEnabled = true
            root.chart.isScaleYEnabled = false
            root.chart.axisLeft.textColor = Color.BLACK
            root.chart.xAxis.textColor = Color.BLACK
            root.chart.axisLeft.setDrawAxisLine(false)
            root.chart.xAxis.setDrawAxisLine(false)
            root.chart.setDrawMarkers(false)
            root.chart.xAxis.labelCount = 5

            val leftAxis = root.chart.axisLeft
            leftAxis.axisMinimum = 0f // always start at zero
            val maxObject: Entry = entries.maxBy { it.y }!! // entries is not empty here
            leftAxis.axisMaximum = max(a = maxObject.y, b = 100f) + 15f // 15% margin on top
            val targetLine = LimitLine(100f, "")
            targetLine.enableDashedLine(5f, 5f, 0f)
            leftAxis.addLimitLine(targetLine)

            val rightAxis = chart.axisRight
            rightAxis.setDrawGridLines(false)
            rightAxis.setDrawZeroLine(false)
            rightAxis.setDrawAxisLine(false)
            rightAxis.setDrawLabels(false)

            val dataSet = LineDataSet(entries, "Label")
            dataSet.setDrawCircles(false)
            dataSet.lineWidth = 2.5f
            dataSet.color = ContextCompat.getColor(requireContext(), R.color.teal_200)
            dataSet.setDrawFilled(true)
            dataSet.fillDrawable = getDrawable(requireContext(), R.drawable.graph_fill_gradiant)
            dataSet.setDrawValues(false)
            dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER

            val lineData = LineData(dataSet)
            root.chart.xAxis.valueFormatter = (ChartXValueFormatter(dateArray))
            root.chart.data = lineData
            root.chart.invalidate()
        }
        return root
    }

}