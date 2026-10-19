package com.example.timerapp

import android.app.Activity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class AlarmFullScreenActivity : Activity() {
    private val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Show over lock screen and turn screen on
        window.addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        )
        setContentView(R.layout.activity_alarm_fullscreen)

        val tvTime = findViewById<TextView>(R.id.tvAlarmTime)
        tvTime.text = timeFormat.format(Date())

        findViewById<Button>(R.id.btnDismiss).setOnClickListener { finish() }
    }
}
