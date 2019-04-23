package com.egabruskiy.tasktimer.mvp.presenter

import android.graphics.Color
import android.graphics.Typeface
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.egabruskiy.tasktimer.mvp.model.BD
import com.egabruskiy.tasktimer.mvp.view.StatisticView
import com.egabruskiy.tasktimer.util.MyPieFormatter
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.ArrayList

@InjectViewState
class StatisticPresenter: MvpPresenter<StatisticView>() {


    private var tf: Typeface? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setPieChart()
    }


   fun setPieChart() {
        val entries = ArrayList<PieEntry>()
        if (BD.taskList.isEmpty()) {
            entries.add(PieEntry(0.toFloat(), "name2"))
        }else {
            for (task in BD.taskList){
                entries.add(PieEntry(task.allProgress.toFloat(),task.taskName))
            }
        }

        var dataSet = PieDataSet(entries,"")

       val colors = ArrayList<Int>()
        for (c in ColorTemplate.COLORFUL_COLORS)
            colors.add(c)
//       colors.add(ColorTemplate.getHoloBlue())
       dataSet.colors = colors
       dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
       //reduce view
       dataSet.selectionShift = 27f

       dataSet.valueLinePart1OffsetPercentage = 60f
       dataSet.valueLinePart1Length = 0.4f
       dataSet.valueLinePart2Length = 0.4f


       val data = PieData(dataSet)
        data.setValueFormatter(MyPieFormatter())
       data.setValueTextSize(22f)
       data.setValueTextColor(Color.BLACK)
       data.setValueTypeface(tf)

        viewState.setChartData(data)
    }



}