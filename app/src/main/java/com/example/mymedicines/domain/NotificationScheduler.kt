package com.example.mymedicines.domain

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.example.mymedicines.NotificationUtils
import java.util.Calendar

object NotificationScheduler {
    fun scheduleNotificationAtSpecificTime(
        context: Context,
        hourOfDay: Int,
        minute: Int,
        logTag: String = "NotificationScheduler"
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Устанавливаем календарь на нужное время
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)

            // Если время уже прошло сегодня, планируем на завтра
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        // Сохраняем информацию о запланированном уведомлении
        NotificationUtils.saveScheduledNotification(
            context,
            hourOfDay,
            minute,
            calendar.timeInMillis
        )

        // Устанавливаем alarm с учетом версии Android
        setAlarm(alarmManager, calendar.timeInMillis, pendingIntent)

        val timeText = String.format("%02d:%02d", hourOfDay, minute)
        Log.d(logTag, "Уведомление запланировано на $timeText")
    }

    private fun setAlarm(
        alarmManager: AlarmManager,
        triggerTime: Long,
        pendingIntent: PendingIntent
    ) {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            }

            else -> {
                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            }
        }
    }
}