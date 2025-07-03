package com.example.mymedicines.view.medecine.newMed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import com.example.mymedicines.MainActivity
import com.example.mymedicines.databinding.FragmentNewMedecineBinding
import com.example.mymedicines.databinding.FragmentRecyclerViewBinding

class NewMedecineFragment : Fragment() {
    private var _binding: FragmentNewMedecineBinding? = null
    private val viewModel: NewMedecineViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewMedecineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.returnFab.setOnClickListener {
            //подготавливаем данные для передачи
            val resultData = bundleOf("dataKey" to binding.editText.text.toString())
            //отправка резулььтата
            parentFragmentManager.setFragmentResult("requestKey", resultData)
            //возврат на первый фрагмент
            (activity as? MainActivity)?.popFragmentBack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}