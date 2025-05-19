package com.example.mymedicines

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface ItemRepository {
    val dataFlow: StateFlow<List<Item>>
     fun addItem(item: Item)
}