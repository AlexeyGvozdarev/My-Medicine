package com.example.mymedicines

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

 class RvFragmentViewModel(private val repository: ItemRepository):ViewModel() {
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
     fun addNewItem(items: Item) {
         Log.d("TAG", "addItems: $items")
         viewModelScope.launch {
             repository.addItem(items)

         }
     }
}





