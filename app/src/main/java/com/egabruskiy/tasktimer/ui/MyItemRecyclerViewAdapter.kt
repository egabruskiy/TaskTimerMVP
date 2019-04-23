package com.egabruskiy.tasktimer.ui

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.egabruskiy.tasktimer.R

import com.egabruskiy.tasktimer.mvp.presenter.DayListPresenter
import com.egabruskiy.tasktimer.mvp.view.RepoRowView
import kotlinx.android.synthetic.main.fragment_item.view.*


class MyItemRecyclerViewAdapter(private var adapterListPresenter:DayListPresenter.RepoListPresenter)
    : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        adapterListPresenter.bindRepoListRow(position, holder)
        val mContext = holder.itemView.context
        holder.parentLayout.setOnClickListener {
            val intent = Intent(mContext, TaskActivity::class.java).apply {
               putExtra("index",position)
            }
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = adapterListPresenter.repoCount()

    inner class ViewHolder( mView: View) : RecyclerView.ViewHolder(mView),RepoRowView {
        override fun setProgress(progress: Long, plan: Long) {
            progressItem.max = plan.toInt()
            progressItem.progress = progress.toInt()
        }

        override fun setTask(title: String) {
            taskNameView.text = title
        }

        override fun setTime(time: String) {
            dayTimeView.text = time
        }

        private  val progressItem: ProgressBar = mView.progress_bar_item
        private  val taskNameView: TextView = mView.item_task_name
        private  val dayTimeView: TextView = mView.progress_time_in_day
          val parentLayout: LinearLayout = mView.parent_layout

    }

}

