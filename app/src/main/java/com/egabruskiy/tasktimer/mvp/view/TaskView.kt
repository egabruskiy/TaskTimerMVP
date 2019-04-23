package com.egabruskiy.tasktimer.mvp.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface TaskView : MvpView {


    fun setBtnVisibility (vision : Int)
    fun setTaskTitle (title : String)
    fun setProgressBarMax (max : Int)
    fun setProgressBarProgress(progress:Int)
    fun setTimeTitle (time : String)
    fun setTitlesEnable(enable:Boolean)
    fun goToList()
    fun alarm()
}