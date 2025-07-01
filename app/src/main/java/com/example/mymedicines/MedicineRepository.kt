package com.example.mymedicines

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MedicineRepository : ItemRepository {
    private val _dataFlow = MutableStateFlow<List<Item>>(emptyList())
    override val dataFlow: StateFlow<List<Item>> = _dataFlow.asStateFlow()

    init {
        _dataFlow.value = (
                listOf(
                    Item("Ibuprofen"),
                    Item("Paracetamol"),
                    Item("Aspirin"),
                    Item("Amoxicillin"),
                    Item("Metformin"),
                    Item("Atorvastatin"),
                    Item("Omeprazole"),
                    Item("Losartan"),
                    Item("Gabapentin"),
                    Item("Levothyroxine"),
                    Item("Prednisone"),
                    Item("Sertraline"),
                    Item("Lisinopril"),
                    Item("Furosemide"),
                    Item("Ciprofloxacin"),
                    Item("Simvastatin"),
                    Item("Azithromycin"),
                    Item("Tramadol"),
                    Item("Doxycycline"),
                    Item("Amlodipine"),
                    Item("Clopidogrel"),
                    Item("Cetirizine"),
                    Item("Ranitidine"),
                    Item("Metoprolol"),
                    Item("Fluoxetine"),
                    Item("Warfarin"),
                    Item("Hydrochlorothiazide"),
                    Item("Alprazolam"),
                    Item("Diazepam"),
                    Item("Hydrocodone"),
                    Item("Tamsulosin"),
                    Item("Meloxicam"),
                    Item("Lorazepam"),
                    Item("Topiramate"),
                    Item("Venlafaxine"),
                    Item("Bupropion"),
                    Item("Naproxen"),
                    Item("Duloxetine"),
                    Item("Carvedilol"),
                    Item("Montelukast")
                )
                )
    }

    override fun addItem(item: Item) {
        //addItemEmit(item)
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
        if (item.number == null) return
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