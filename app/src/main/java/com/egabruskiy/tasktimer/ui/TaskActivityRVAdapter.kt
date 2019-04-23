//package com.egabruskiy.tasktimer.ui
//
//import android.content.Intent
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.LinearLayout
//import android.widget.ProgressBar
//import android.widget.TextView
//import com.egabruskiy.tasktimer.R
//import com.egabruskiy.tasktimer.mvp.presenter.DayListPresenter
//import com.egabruskiy.tasktimer.mvp.view.RepoRowView
//import com.egabruskiy.tasktimer.mvp.view.TaskRowView
//import kotlinx.android.synthetic.main.fragment_item.view.*
//import kotlinx.android.synthetic.main.task_item.view.*
//
//class TaskActivityRVAdapter ()
//: RecyclerView.Adapter<TaskActivityRVAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//                .inflate(R.layout.fragment_item, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
////        adapterListPresenter.bindRepoListRow(position, holder)
//    }
//
//    override fun getItemCount(): Int = 1
////    override fun getItemCount(): Int = adapterListPresenter.repoCount()
//
//    inner class ViewHolder( mView: View) : RecyclerView.ViewHolder(mView),TaskRowView {
//
//        override fun setDate(time: String) {
//            tmeDateView.text = time
//        }
//
//        private  val tmeDateView: TextView = mView.task_item_date
//
//    }
//
//}
