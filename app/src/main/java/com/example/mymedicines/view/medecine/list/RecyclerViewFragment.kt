package com.example.mymedicines.view.medecine.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymedicines.MainActivity
import com.example.mymedicines.R
import com.example.mymedicines.databinding.FragmentRecyclerViewBinding
import com.example.mymedicines.domain.MedicineRepository
import com.example.mymedicines.model.Item
import com.example.mymedicines.view.medecine.newMed.NewMedecineFragment

class RecyclerViewFragment : Fragment(R.layout.fragment_recycler_view) {

    private var _binding: FragmentRecyclerViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RvFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val repository = MedicineRepository()

        // val viewModelFactory = MedecineViewModelFactory(repository)
        viewModel =
            ViewModelProvider(this/*, viewModelFactory*/).get(RvFragmentViewModel::class.java)

        // Инициализация View Binding
        _binding = FragmentRecyclerViewBinding.bind(view)

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
