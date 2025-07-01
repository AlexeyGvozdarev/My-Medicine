package com.example.mymedicines.domain

import com.example.mymedicines.model.Item
import kotlinx.coroutines.flow.StateFlow

interface ItemRepository {
    val dataFlow: StateFlow<List<Item>>
     fun addItem(item: Item)
}