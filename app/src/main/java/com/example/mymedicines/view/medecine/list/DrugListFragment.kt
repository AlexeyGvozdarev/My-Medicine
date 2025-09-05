package com.example.mymedicines.view.medecine.list

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymedicines.AppComponents
import com.example.mymedicines.MainActivity
import com.example.mymedicines.R
import com.example.mymedicines.databinding.FragmentDruglistViewBinding
import com.example.mymedicines.domain.MedicineRepository
import com.example.mymedicines.view.core.ViewModelFactory
import com.example.mymedicines.view.medecine.newMed.NewMedecineFragment

class DrugListFragment : Fragment(R.layout.fragment_druglist_view) {
    private val channelId = "NAV_CHANNEL"
    private val notificationId = 1001

    private var _binding: FragmentDruglistViewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DrugListViewModel by viewModels {
        ViewModelFactory {
            DrugListViewModel(AppComponents.medicineRepository)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация View Binding
        _binding = FragmentDruglistViewBinding.bind(view)

        // Настройка RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = MedicineAdapter(emptyList())
        binding.recyclerView.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner, Observer { items ->
            adapter.updateItems(items)
        })

        binding.fab.setOnClickListener() {
            (activity as? MainActivity)?.replaceFragment(NewMedecineFragment())
        }
        binding.informationFab.setOnClickListener() {
            createNotification()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        // Очищаем binding при уничтожении View
        _binding = null
    }

    private fun createNotification() {
        // Intent для открытия MainActivity с флагом перехода
        val intent = Intent(requireContext(), MainActivity::class.java).apply {
            putExtra("OPEN_INFORMATIONFRAGMENT", true)
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            requireContext(),
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Создание уведомления
        val notification = NotificationCompat.Builder(requireContext(), channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Переход на фрагмент информации")
            .setContentText("Нажмите для перехода")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        // Показ уведомления
        val notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notification)
    }
}
