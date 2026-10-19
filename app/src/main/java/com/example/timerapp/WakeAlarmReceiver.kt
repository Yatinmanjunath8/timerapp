package com.example.timerapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import android.util.Log

class WakeAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val type = intent.getStringExtra("type") ?: "ALARM"
        // Acquire a wake lock to ensure CPU stays awake briefly
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TimerApp:WakeLock")
        wakeLock.acquire(3000) // 3 seconds

        when (type) {
            "POWER_ON" -> {
                // Start overlay service to show clock (if not already running)
                val serviceIntent = Intent(context, OverlayService::class.java)
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    context.startForegroundService(serviceIntent)
                } else {
                    context.startService(serviceIntent)
                }
            }
            "ALARM" -> {
                // Launch full‑screen alarm activity
                val alarmIntent = Intent(context, AlarmFullScreenActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
                context.startActivity(alarmIntent)
            }
            else -> Log.w("WakeAlarmReceiver", "Unknown type: $type")
        }
        wakeLock.release()
    }
}
