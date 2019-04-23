package com.egabruskiy.tasktimer

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.egabruskiy.tasktimer.auth.ChooserActivity
import com.egabruskiy.tasktimer.auth.EmailPasswordActivity
import com.egabruskiy.tasktimer.ui.DayListFragment
import com.egabruskiy.tasktimer.ui.StatisticsFragment
import com.egabruskiy.tasktimer.ui.TaskActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

  private val fragment1: DayListFragment = DayListFragment.newInstance()
  private val fragment2: StatisticsFragment = StatisticsFragment.newInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val fab: View = findViewById(R.id.fab_add_task)

        fab.setOnClickListener { val intent = Intent(this, TaskActivity::class.java)
            startActivity(intent)
        }

        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer,fragment2)
                .hide(fragment2)
                .add(R.id.fragmentContainer,fragment1)
                .commit()

        bottomNavigationView.setOnNavigationItemSelectedListener {
            selectItem(it)
        }


    }

    private fun selectItem(item: MenuItem) : Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                        .hide(fragment2)
                        .show(fragment1)
                        .commit()
                supportActionBar?.title = "TaskList"
                fab_add_task.show()
            }

            R.id.navigation_dashboard ->{
                supportFragmentManager.beginTransaction()
                        .hide(fragment1)
                        .show(fragment2)
                        .commit()
                    fragment2.update()

                    supportActionBar?.title = "Statistic"
                fab_add_task.hide()
            }
            }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main2, menu)
        if (FirebaseAuth.getInstance().currentUser!!.isAnonymous){
            menu.clear()
            menu.add(2, Menu.FIRST +1, Menu.NONE, "Create Account").isVisible
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sign_out ->{
                getAlertDialog("Do you want to sign out?",1).show()
                return true
            }

            2 ->{
                getAlertDialog("Do you want to create an account?",2).show()
                    return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


    private fun getAlertDialog(question: String,action:Int): AlertDialog {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(question)
        builder.setPositiveButton("YES") { dialog, which ->
            if (action == 1 ) {
                FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this, ChooserActivity::class.java)
                startActivity(intent)
            }
            if (action == 2){
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, EmailPasswordActivity::class.java)
                startActivity(intent)
            }
        }
        builder.setNegativeButton("No") { dialog, which -> }
        return builder.create()
    }


}
