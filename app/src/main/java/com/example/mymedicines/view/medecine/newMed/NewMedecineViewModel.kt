package com.example.mymedicines.view.medecine.newMed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymedicines.AppComponents
import com.example.mymedicines.domain.ItemRepository
import com.example.mymedicines.domain.MedicineRepository
import com.example.mymedicines.model.Item
import kotlinx.coroutines.launch

class NewMedecineViewModel(repository: ItemRepository) : ViewModel() {

    val repository = MedicineRepository()


    fun addNewIItem(item: String) {
        val newItem = Item(item)

        viewModelScope.launch {
            repository.addItem(newItem)
        }
    }

}