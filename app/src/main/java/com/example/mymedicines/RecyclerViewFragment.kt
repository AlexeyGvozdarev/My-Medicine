package com.example.mymedicines

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymedicines.databinding.FragmentRecyclerViewBinding

class RecyclerViewFragment : Fragment(R.layout.fragment_recycler_view) {

    // Переменная для View Binding
    private var _binding: FragmentRecyclerViewBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация View Binding
        _binding = FragmentRecyclerViewBinding.bind(view)

        // Настройка RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Создаем список данных
        val items = listOf(
            Item("Ibuprofen"),
            Item("Paracetamol"),
            Item("Aspirin"),
            Item("Amoxicillin"),
            Item("Metformin"),
            Item("Atorvastatin"),
            Item("Omeprazole"),
            Item("Losartan"),
            Item("Gabapentin"),
            Item("Levothyroxine"),
            Item("Prednisone"),
            Item("Sertraline"),
            Item("Lisinopril"),
            Item("Furosemide"),
            Item("Ciprofloxacin"),
            Item("Simvastatin"),
            Item("Azithromycin"),
            Item("Tramadol"),
            Item("Doxycycline"),
            Item("Amlodipine"),
            Item("Clopidogrel"),
            Item("Cetirizine"),
            Item("Ranitidine"),
            Item("Metoprolol"),
            Item("Fluoxetine"),
            Item("Warfarin"),
            Item("Hydrochlorothiazide"),
            Item("Alprazolam"),
            Item("Diazepam"),
            Item("Hydrocodone"),
            Item("Tamsulosin"),
            Item("Meloxicam"),
            Item("Lorazepam"),
            Item("Topiramate"),
            Item("Venlafaxine"),
            Item("Bupropion"),
            Item("Naproxen"),
            Item("Duloxetine"),
            Item("Carvedilol"),
            Item("Montelukast")
        )

        // Настраиваем адаптер
        val adapter = MedicineAdapter(items)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Очищаем binding при уничтожении View
        _binding = null
    }
}
