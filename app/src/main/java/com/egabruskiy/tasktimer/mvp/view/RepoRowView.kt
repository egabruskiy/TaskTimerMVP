package com.egabruskiy.tasktimer.mvp.view




interface RepoRowView {
    fun setTask(title: String)
    fun setTime(time: String)
    fun setProgress(progress:Long,plan:Long)
}