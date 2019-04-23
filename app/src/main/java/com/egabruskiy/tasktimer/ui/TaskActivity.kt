package com.egabruskiy.tasktimer.ui

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AlertDialog
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.egabruskiy.tasktimer.MainActivity
import com.egabruskiy.tasktimer.R
import com.egabruskiy.tasktimer.mvp.model.Task
import com.egabruskiy.tasktimer.mvp.presenter.TaskPresenter
import com.egabruskiy.tasktimer.mvp.view.TaskView
import kotlinx.android.synthetic.main.activity_task.*
import android.view.Menu
import android.view.MenuItem
import android.media.RingtoneManager


class TaskActivity : MvpAppCompatActivity(),TaskView{

    @InjectPresenter
    lateinit var presenter: TaskPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        setSupportActionBar(toolbar_task)
        supportActionBar?.isShowing
//        supportActionBar?.title = "444"

       var position =  intent.getIntExtra("index",-1)

        presenter.getPosition(position)
        progress_bar_task.progressDrawable
//                    root_layout.setBackgroundColor(Color.RED)

        button_start.setOnClickListener { presenter.startTimer() }
        button_stop.setOnClickListener { presenter.stop() }
        button_save.setOnClickListener { presenter.getTextAndSaveTask(task_name.text.toString(),
                task_time_in_day.text.toString()) }
        button_reset.setOnClickListener{getAlertDialog("Do you want to reset your progress?",2).show() }
    }



   override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when  (item.itemId) {
            R.id.delete_item -> getAlertDialog("Do you want to delete this task?",1).show()
            R.id.reset_time -> getAlertDialog("Do you want to reset your progress?",2).show()
//            R.id.change ->  presenter.doChanges()
        }
        return true
    }

    override fun setBtnVisibility(vision: Int) {
        button_save.visibility = vision
    }

    override fun setTaskTitle(title: String) {
        task_name.setText(title)
    }

    override fun setProgressBarMax(max: Int) {
        progress_bar_task.max = max
    }

    override fun setProgressBarProgress(progress: Int) {
        progress_bar_task.progress = progress
    }

    override fun setTimeTitle(time: String) {
        task_time_in_day.setText(time)
    }

    override fun setTitlesEnable(enable: Boolean) {
        task_time_in_day.isEnabled = enable
        task_name.isEnabled = enable
    }

    override fun goToList() {
        val intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    override fun alarm(){
        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val r = RingtoneManager.getRingtone(applicationContext, notification)
        r.play()
    }

    private fun getAlertDialog(question: String,action:Int): AlertDialog {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(question)
        builder.setPositiveButton("YES") { dialog, which ->
            if (action == 1 ) {
                presenter.deleteTask()
            }
            if (action == 2){
            presenter.reset()
        }

        }
        builder.setNegativeButton("No") { dialog, which -> }
        return builder.create()
    }
    override fun onBackPressed() {
        val intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
    }
}
