package com.egabruskiy.tasktimer.mvp.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.egabruskiy.tasktimer.mvp.model.BD
import com.egabruskiy.tasktimer.mvp.model.TasksRepository
import com.egabruskiy.tasktimer.mvp.view.DayListView
import com.egabruskiy.tasktimer.mvp.view.RepoRowView
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

@InjectViewState
class DayListPresenter(private val scheduler: Scheduler):MvpPresenter<DayListView>() {

    val repoListPresenter : RepoListPresenter  = RepoListPresenter()

    inner class RepoListPresenter  {

        fun repoCount() = BD.taskList.size
        fun bindRepoListRow(position: Int, rowView: RepoRowView) {
                if (BD.taskList.size !=0) {
                    rowView.setTask(BD.taskList[position].taskName)
                    rowView.setTime((BD.taskList[position].currentProgress/60000).toString()
                            + "/" + (BD.taskList[position].timeDayPlan/60000).toString())
                    rowView.setProgress(BD.taskList[position].timeDayPlan -BD. taskList[position].currentProgress,
                            BD.taskList[position].timeDayPlan)
                }
        }
    }


    @SuppressLint("CheckResult")
    fun getList(){
        BD.taskList.clear()

        TasksRepository.getTasks()
                .subscribeOn(Schedulers.newThread())
                .observeOn(scheduler)
                .doOnComplete {
                    viewState.updateRepoList()
                }
                .subscribe{
                    BD.taskList=it
                }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getList()
        viewState.init()
    }

}