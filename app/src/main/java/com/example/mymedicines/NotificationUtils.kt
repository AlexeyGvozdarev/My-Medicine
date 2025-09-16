package com.example.mymedicines

import android.content.Context
import android.util.Log

object NotificationUtils {
    private const val PREFS_NAME = "notifications_prefs"
    private const val KEY_SCHEDULED_HOUR = "scheduled_notification_hour"
    private const val KEY_SCHEDULED_MINUTE = "scheduled_notification_minute"
    private const val KEY_SCHEDULED_TIME = "scheduled_notification_time"

    // Сохраняем время запланированного уведомления
    fun saveScheduledNotification(context: Context, hour: Int, minute: Int, triggerTime: Long) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putInt(KEY_SCHEDULED_HOUR, hour)
        editor.putInt(KEY_SCHEDULED_MINUTE, minute)
        editor.putLong(KEY_SCHEDULED_TIME, triggerTime)
        editor.apply()
        Log.d(
            "Notification",
            "Сохранено уведомление на $hour:$minute, время срабатывания: $triggerTime"
        )
    }

    // Получаем сохраненное время уведомления
    fun getScheduledNotification(context: Context): Triple<Int, Int, Long>? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val hour = prefs.getInt(KEY_SCHEDULED_HOUR, -1)
        val minute = prefs.getInt(KEY_SCHEDULED_MINUTE, -1)
        val triggerTime = prefs.getLong(KEY_SCHEDULED_TIME, 0)

        return if (hour != -1 && minute != -1) {
            Triple(hour, minute, triggerTime)
        } else {
            null
        }
    }

    // Очищаем сохраненное уведомление
    fun clearScheduledNotification(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.remove(KEY_SCHEDULED_HOUR)
        editor.remove(KEY_SCHEDULED_MINUTE)
        editor.remove(KEY_SCHEDULED_TIME)
        editor.apply()
        Log.d("Notification", "Сохраненное уведомление очищено")
    }
}