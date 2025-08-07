package com.example.mymedicines.view.medecine.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymedicines.AppComponents
import com.example.mymedicines.MainActivity
import com.example.mymedicines.R
import com.example.mymedicines.databinding.FragmentDruglistViewBinding
import com.example.mymedicines.domain.MedicineRepository
import com.example.mymedicines.view.medecine.newMed.NewMedecineFragment

class DrugListFragment : Fragment(R.layout.fragment_druglist_view) {

    private var _binding: FragmentDruglistViewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DrugListViewModel by viewModels {
        MedecineViewModelFactory(AppComponents.medicineRepository)
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
            parentFragmentManager.setFragmentResultListener(
                "requestKey",
                this
            ) { requestKey, bundle ->
                if (requestKey == "requestKey") {
                    val resultString = bundle.getString("dataKey", "")
                    viewModel.addNewItem(resultString)
                }
            }


        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        // Очищаем binding при уничтожении View
        _binding = null
    }
}
