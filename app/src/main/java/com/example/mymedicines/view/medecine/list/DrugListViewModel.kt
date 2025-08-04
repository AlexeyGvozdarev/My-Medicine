package com.example.mymedicines.view.medecine.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymedicines.model.Item
import com.example.mymedicines.domain.MedicineRepository
import kotlinx.coroutines.launch

class DrugListViewModel() : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    val repository = MedicineRepository()

    init {
        viewModelScope.launch {
            repository.dataFlow.collect { newItems ->
                Log.d("TAG", "collect: $newItems")
                _items.value = newItems
            }
        }
    }

    fun addNewItem(item: String) {
        val newItem = Item(item)

        Log.d("TAG", "addItems: $items")
        viewModelScope.launch {
            repository.addItem(newItem)

        }
    }
}







