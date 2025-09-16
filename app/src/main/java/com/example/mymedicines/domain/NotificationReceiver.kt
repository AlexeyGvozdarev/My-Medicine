package com.example.mymedicines.domain

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.mymedicines.MainActivity
import com.example.mymedicines.NotificationUtils
import com.example.mymedicines.R

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("NotificationReceiver", "Уведомление сработало в: ${System.currentTimeMillis()}")

        // Показываем уведомление
        showNotification(context)

        // Перепланируем уведомление на следующий день
        val notificationData = NotificationUtils.getScheduledNotification(context)
        if (notificationData != null) {
            val (hour, minute, _) = notificationData
            Log.d("NotificationReceiver", "Перепланируем уведомление на $hour:$minute")
            scheduleNotificationAtSpecificTime(context, hour, minute)
        } else {
            Log.d("NotificationReceiver", "Нет данных для перепланирования уведомления")
        }
    }

    private fun showNotification(context: Context) {
        // Создаем канал уведомлений (для Android 8.0+)
        createNotificationChannel(context)

        // Intent для открытия MainActivity с переходом на другой фрагмент
        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra("OPEN_NEWMEDECCINEFRAGMENT", true)
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Создаем уведомление
        val notification = NotificationCompat.Builder(context, "NAV_CHANNEL")
            .setSmallIcon(R.drawable.pill__1_)
            .setContentTitle("Ежедневное уведомление")
            .setContentText("Нажмите для перехода на NewMedFrag фрагмент")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setColor(ContextCompat.getColor(context, R.color.purple_500))
            .build()

        // Показываем уведомление
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1001, notification)

        Log.d("NotificationReceiver", "Уведомление показано")
    }

    private fun createNotificationChannel(context: Context) {
        // Создаем канал уведомлений только для Android 8.0+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "NAV_CHANNEL",
                "Навигационные уведомления",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Канал для навигационных уведомлений между фрагментами"
            }

            // Регистрируем канал в системе
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
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

        // Устанавливаем alarm с учетом версии Android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                android.app.AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
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
        Log.d("NotificationReceiver", "Уведомление перепланировано на $timeText")
    }
}