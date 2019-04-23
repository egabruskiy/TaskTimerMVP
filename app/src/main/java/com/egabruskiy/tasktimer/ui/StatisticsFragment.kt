package com.egabruskiy.tasktimer.ui

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.egabruskiy.tasktimer.R
import com.egabruskiy.tasktimer.mvp.model.BD
import com.egabruskiy.tasktimer.mvp.presenter.DayListPresenter
import com.egabruskiy.tasktimer.mvp.presenter.StatisticPresenter
import com.egabruskiy.tasktimer.mvp.view.StatisticView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_statistics.*
import java.util.*


class StatisticsFragment : MvpAppCompatFragment(),StatisticView {


    @InjectPresenter
    lateinit var presenter: StatisticPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_statistics, container, false)



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        chart.setUsePercentValues(false)
        chart.description.isEnabled = false
        chart.setExtraOffsets(2f, 100f, 5f, 5f)
        chart.dragDecelerationFrictionCoef = 0.95f

        chart.setEntryLabelTextSize(24f)
        chart.setEntryLabelColor(Color.BLACK)
//        chart.setEntryLabelTypeface()
        chart.centerText = generateCenterSpannableText()

        chart.setExtraOffsets(20f, 0f, 20f, 0f)

        chart.isDrawHoleEnabled = true
        chart.setHoleColor(Color.WHITE)

        chart.setTransparentCircleColor(Color.WHITE)
        chart.setTransparentCircleAlpha(110)

        chart.holeRadius = 45f
        chart.transparentCircleRadius = 16f

        chart.setDrawCenterText(true)


        chart.rotationAngle = 20f
//         enable rotation of the chart by touch
        chart.isRotationEnabled = true
        chart.isHighlightPerTapEnabled = true

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);



        chart.animateY(1000, Easing.EaseInOutQuad)
        // chart.spin(2000, 0, 360);

        val l = chart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = false
    }


    private fun generateCenterSpannableText(): SpannableString {

        val s = SpannableString("Your\nProgress")
        s.setSpan(RelativeSizeSpan(2f), 0, 13, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length - 13, s.length, 0)
        return s
    }
    override fun setChartData(data: PieData) {
        chart.data = data
        chart.invalidate()
    }





    fun update(){
       presenter.setPieChart()
    }

        companion object {
        fun newInstance()= StatisticsFragment()

    }

}