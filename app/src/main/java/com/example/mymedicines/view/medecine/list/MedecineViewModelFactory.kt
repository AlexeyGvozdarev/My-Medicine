package com.example.mymedicines.view.medecine.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MedecineViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DrugListViewModel::class.java)) {
            return DrugListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}