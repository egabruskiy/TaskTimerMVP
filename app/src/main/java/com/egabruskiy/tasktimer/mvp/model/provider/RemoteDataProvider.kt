package com.egabruskiy.tasktimer.mvp.model.provider

import com.egabruskiy.tasktimer.mvp.model.Task
import io.reactivex.Observable


interface RemoteDataProvider {

    fun getItems(): Observable<MutableList<Task>>
    fun deleteItem(task: Task)
    fun saveItem(task: Task)
}