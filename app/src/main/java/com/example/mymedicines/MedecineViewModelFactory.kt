package com.example.mymedicines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MedecineViewModelFactory(private val repository: ItemRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RvFragmentViewModel::class.java)) {
            return RvFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}