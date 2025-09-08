package com.example.mymedicines.domain

import android.util.Log
import com.example.mymedicines.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MedicineRepository : ItemRepository {
    private val _dataFlow = MutableStateFlow<List<Item>>(emptyList())
    override val dataFlow: StateFlow<List<Item>> = _dataFlow.asStateFlow()

    override fun addItem(item: Item) {
        addItemUpdate(item)
    }

    private suspend fun addItemEmit(item: Item) {
        if (item.number == null) return
        val value = _dataFlow.value
        Log.d("TAG", "addItem: $value")
        val mutableValue = value.toMutableList()
        val isNumEven = item.isEven()
        if (isNumEven) {
            mutableValue.add(0, item)
        } else {
            Log.d("TAG", "Number: $isNumEven")
            mutableValue.add(item)

        }
        _dataFlow.emit(mutableValue)
    }

    private fun addItemUpdate(item: Item) {
        _dataFlow.update { currentList ->
            val updatedList = currentList.toMutableList()
            val isNumEven = item.isEven()

            if (isNumEven) {
                updatedList.add(0, item)
            } else {
                updatedList.add(item)
            }
            updatedList
        }
    }
}