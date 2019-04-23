package com.egabruskiy.tasktimer.mvp.model

import android.util.Log
import com.egabruskiy.tasktimer.mvp.model.provider.FireStoreProvider
import io.reactivex.Observable


object TasksRepository{

    private val remoteDataProvider = FireStoreProvider()


    fun getTasks(): Observable<MutableList<Task>> = remoteDataProvider.getItems()
    fun saveTask(task: Task)=remoteDataProvider.saveItem(task)
    fun deleteTask(task: Task) = remoteDataProvider.deleteItem(task)

}