package com.example.mymedicines.view.medecine.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import com.example.mymedicines.NotificationUtils
import com.example.mymedicines.databinding.FragmentInformationBinding
import com.example.mymedicines.domain.NotificationReceiver

class InformationFragment : Fragment() {
    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!

    private lateinit var timePicker: TimePicker
    private lateinit var scheduleButton: Button
    private lateinit var cancelButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timePicker = binding.timePicker
        scheduleButton = binding.btnSchedule
        cancelButton = binding.btnCancel

        // Устанавливаем 24-часовой формат
        timePicker.setIs24HourView(true)

        scheduleButton.setOnClickListener {
            val hour = timePicker.hour
            val minute = timePicker.minute
            scheduleNotificationAtSpecificTime(hour, minute)
        }

        cancelButton.setOnClickListener {
            cancelScheduledNotification()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun scheduleNotificationAtSpecificTime(hourOfDay: Int, minute: Int) {
        val alarmManager =
            requireContext().getSystemService(android.content.Context.ALARM_SERVICE) as android.app.AlarmManager
        val intent = android.content.Intent(requireContext(), NotificationReceiver::class.java)
        val pendingIntent = android.app.PendingIntent.getBroadcast(
            requireContext(),
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
            requireContext(),
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
        Toast.makeText(
            requireContext(),
            "Уведомление запланировано на $timeText",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun cancelScheduledNotification() {
        val alarmManager =
            requireContext().getSystemService(android.content.Context.ALARM_SERVICE) as android.app.AlarmManager
        val intent = android.content.Intent(requireContext(), NotificationReceiver::class.java)
        val pendingIntent = android.app.PendingIntent.getBroadcast(
            requireContext(),
            0,
            intent,
            android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.cancel(pendingIntent)
        NotificationUtils.clearScheduledNotification(requireContext())
        Toast.makeText(requireContext(), "Уведомление отменено", Toast.LENGTH_SHORT).show()
    }
}