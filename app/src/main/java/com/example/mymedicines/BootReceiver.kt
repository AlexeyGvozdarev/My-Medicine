package com.example.mymedicines

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.mymedicines.domain.NotificationReceiver

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED ||
            intent.action == "android.intent.action.QUICKBOOT_POWERON"
        ) {
            Log.d("BootReceiver", "Устройство перезагружено, восстанавливаем уведомление")

            // Даем системе время на запуск
            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                restoreScheduledNotification(context)
            }, 10000) // 10 секунд задержки
        }
    }

    private fun restoreScheduledNotification(context: Context) {
        val notificationData = NotificationUtils.getScheduledNotification(context)
        if (notificationData != null) {
            val (hour, minute, _) = notificationData
            Log.d("BootReceiver", "Восстанавливаем уведомление на $hour:$minute")
            scheduleNotificationAtSpecificTime(context, hour, minute)
        } else {
            Log.d("BootReceiver", "Нет сохраненных уведомлений для восстановления")
        }
    }

    private fun scheduleNotificationAtSpecificTime(context: Context, hourOfDay: Int, minute: Int) {
        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as android.app.AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = android.app.PendingIntent.getBroadcast(
            context,
            0,
            intent,
            android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_IMMUTABLE
        )

        // Устанавливаем календарь на нужное время
        val calendar = java.util.Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(java.util.Calendar.HOUR_OF_DAY, hourOfDay)
            set(java.util.Calendar.MINUTE, minute)
            set(java.util.Calendar.SECOND, 0)

            // Если время уже прошло сегодня, планируем на завтра
            if (timeInMillis <= System.currentTimeMillis()) {
                add(java.util.Calendar.DAY_OF_YEAR, 1)
            }
        }

        // Сохраняем информацию о запланированном уведомлении
        NotificationUtils.saveScheduledNotification(
            context,
            hourOfDay,
            minute,
            calendar.timeInMillis
        )

        // Устанавливаем alarm
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                android.app.AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(
                android.app.AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        } else {
            alarmManager.set(
                android.app.AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }

        val timeText = String.format("%02d:%02d", hourOfDay, minute)
        Log.d("BootReceiver", "Уведомление запланировано на $timeText")
    }
}