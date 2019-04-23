package com.egabruskiy.tasktimer.util

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.utils.ViewPortHandler
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit

class MyPieFormatter : IValueFormatter {


    override fun getFormattedValue(value: Float, entry: Entry, dataSetIndex: Int, viewPortHandler: ViewPortHandler): String {
        return msToHHMM(value.toLong())
    }


    private fun msToHHMM(ms: Long):String{
        var hour = TimeUnit.MILLISECONDS.toHours(ms)
        var min = TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(hour)
//        var sec = TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(min)

        if ( ms >= 3600000) {
            return "$hour"+"h:"+"$min"+"m"
        }
        return "$min"+"m"
    }
}