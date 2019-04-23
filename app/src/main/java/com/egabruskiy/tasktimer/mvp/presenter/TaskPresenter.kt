package com.egabruskiy.tasktimer.mvp.presenter

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.egabruskiy.tasktimer.mvp.model.BD
import com.egabruskiy.tasktimer.mvp.model.Task
import com.egabruskiy.tasktimer.mvp.model.TasksRepository
import com.egabruskiy.tasktimer.mvp.view.TaskView
import java.util.*
import java.util.concurrent.TimeUnit

@InjectViewState
class TaskPresenter : MvpPresenter<TaskView>() {

    private var taskName : String = ""
    private var newTime : String =""
    private var timer: CountDownTimer? = null
    private var startTime: Long = 0
    private var currentTime: Long = 0
    private var timerRunning: Boolean = false
    private var position : Int = 0
    private var task:Task ? = null
    var progress:Long = 0

    fun getPosition(p: Int) {
        position = p

    }


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        initUi()
    }

    fun initUi() {
        if (position > -1) {
            viewState.setBtnVisibility(View.INVISIBLE)
            viewState.setTitlesEnable(false)
            task = BD.taskList[position]
            initTimer(task!!)

        }else{
            viewState.setBtnVisibility(View.VISIBLE)
        }
    }


   private fun initTimer(task: Task) {

        viewState.setTaskTitle(task.taskName)
        startTime = task.timeDayPlan - task.currentProgress
        viewState.setProgressBarMax(task.timeDayPlan.toInt())
        viewState.setProgressBarProgress(startTime.toInt())
        currentTime = startTime
        viewState.setTimeTitle(msToHHMMSS(currentTime))
    }


    fun startTimer() {
        if (timerRunning) { return }
        timerRunning = true
        timer = object: CountDownTimer(currentTime,
                1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished:Long) {
                currentTime = millisUntilFinished
                viewState.setTimeTitle(msToHHMMSS(currentTime))
                viewState.setProgressBarProgress(currentTime.toInt())
            }
            override fun onFinish() {
                viewState.setTaskTitle("Done!")
                viewState.alarm()
                progress = startTime - currentTime
                saveTask()
            }
        }.start()
    }


    fun stop() {
        if (!timerRunning) { return }
        timerRunning = false
        timer?.cancel()
        progress = startTime - currentTime
        saveTask()
    }

    fun reset(){
        timerRunning = false
        timer?.cancel()
        task!!.currentProgress = 0
        progress = 0
        saveTask()
    }




    fun getTextAndSaveTask(text:String,time:String){
        taskName = text
        newTime = time
        saveTask()
    }


     fun saveTask(){
//        if (taskName.isNullOrBlank()) return
//         if (newTime == ""){
//             newTime = task!!.timeDayPlan.toString()
//         }
         task = task?.copy(
//
//                 taskName = taskName,
//                 timeDayPlan = (newTime.toLong()) * 60000,
                     currentProgress = task!!.currentProgress + progress,
                     allProgress = task!!.allProgress + progress
             ) ?: Task(
                     UUID.randomUUID().toString(),
                     taskName,
                     (newTime.toLong()) * 60000,
                 0,
                 0,
                 Date().time)


        TasksRepository.saveTask(task!!)
        viewState.setBtnVisibility(View.INVISIBLE)
        viewState.setTitlesEnable(false)
        initTimer(task!!)
    }

    fun deleteTask(){
        TasksRepository.deleteTask(task!!)
        viewState.goToList()
    }


//    fun doChanges(){
//
//        viewState.setBtnVisibility(View.VISIBLE)
//        viewState.setTitlesEnable(true)
//        viewState.setTimeTitle(TimeUnit.MILLISECONDS.toMinutes(currentTime).toString())
//
//    }

    fun msToHHMMSS(ms : Long):String{
        var hour = TimeUnit.MILLISECONDS.toHours(ms)
        var min = TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(hour)
        var sec = (TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(min)).toString().takeLast(2)
        if ( ms >= 3600000) {
            return "$hour:$min:$sec"
        }
        return "$min:$sec"
    }


}