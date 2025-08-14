package com.example.mymedicines.view.medecine.newMed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymedicines.domain.ItemRepository

class NewMedecineViewModelFactory(private val repository: ItemRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewMedecineViewModel::class.java)) {
            return NewMedecineViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}