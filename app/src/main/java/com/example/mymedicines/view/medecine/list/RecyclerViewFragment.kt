package com.example.mymedicines.view.medecine.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymedicines.R
import com.example.mymedicines.databinding.FragmentRecyclerViewBinding
import com.example.mymedicines.domain.MedicineRepository

class RecyclerViewFragment : Fragment(R.layout.fragment_recycler_view) {

    // Переменная для View Binding
    private var _binding: FragmentRecyclerViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RvFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = MedicineRepository()

        val viewModelFactory = MedecineViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(RvFragmentViewModel::class.java)

        // Инициализация View Binding
        _binding = FragmentRecyclerViewBinding.bind(view)

        // Настройка RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = MedicineAdapter(emptyList())
        binding.recyclerView.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner, Observer {items->
            adapter.updateItems(items)
        })

        binding.fab.setOnClickListener(){
            viewModel.addNewItem()
            //заменить кнопку на кнопку перехода на новый фрагмент
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        // Очищаем binding при уничтожении View
        _binding = null
    }
}
