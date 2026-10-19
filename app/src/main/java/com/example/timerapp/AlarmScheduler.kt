package com.example.timerapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.Calendar

object AlarmScheduler {
    fun scheduleOneMinuteAlarm(context: Context) {
        val alarmTime = Calendar.getInstance().apply {
            add(Calendar.MINUTE, 1)
        }.timeInMillis
        val intent = Intent(context, WakeAlarmReceiver::class.java).apply {
            putExtra("type", "ALARM")
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            pendingIntent
        )
    }

    fun schedulePowerOn(timeInMillis: Long, context: Context) {
        val intent = Intent(context, WakeAlarmReceiver::class.java).apply {
            putExtra("type", "POWER_ON")
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            1,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            timeInMillis,
            pendingIntent
        )
    }
}
