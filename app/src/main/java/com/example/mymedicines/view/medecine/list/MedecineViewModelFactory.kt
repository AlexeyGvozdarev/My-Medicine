package com.example.mymedicines.view.medecine.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymedicines.domain.ItemRepository

class MedecineViewModelFactory(private val repository: ItemRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DrugListViewModel::class.java)) {
            return DrugListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}