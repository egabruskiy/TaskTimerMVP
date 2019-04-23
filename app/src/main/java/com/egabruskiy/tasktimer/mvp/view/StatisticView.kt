package com.egabruskiy.tasktimer.mvp.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.mikephil.charting.data.PieData


@StateStrategyType(AddToEndSingleStrategy::class)
interface StatisticView : MvpView {


    fun setChartData(data:PieData)
}



