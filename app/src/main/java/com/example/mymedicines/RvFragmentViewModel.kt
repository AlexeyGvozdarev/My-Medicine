package com.example.mymedicines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class RvFragmentViewModel(private val repository: ItemRepository):ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items:LiveData<List<Item>>get() = _items

    fun loadItems(){
        viewModelScope.launch {
            _items.value = repository.getItems()
        }
    }
}