package com.example.mymedicines.view.medecine.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymedicines.AppComponents
import com.example.mymedicines.domain.ItemRepository
import com.example.mymedicines.model.Item
import com.example.mymedicines.domain.MedicineRepository
import kotlinx.coroutines.launch

class DrugListViewModel(private val repository: ItemRepository) : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items


    init {
        viewModelScope.launch {
            repository.dataFlow.collect { newItems ->
                Log.d("TAG", "collect: $newItems")
                _items.value = newItems
            }
        }
    }
}







