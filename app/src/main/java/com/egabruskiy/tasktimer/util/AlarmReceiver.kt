package com.egabruskiy.tasktimer.util

import android.widget.Toast
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Дзинь-дзинь! Пора кормить кота",
                Toast.LENGTH_LONG).show()
    }
}