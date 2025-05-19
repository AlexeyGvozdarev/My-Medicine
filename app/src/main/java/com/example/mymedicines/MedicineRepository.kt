package com.example.mymedicines

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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
    override suspend fun addItem(item: Item) {
        if (item.number == null) return
        val value = _dataFlow.value
        Log.d("TAG", "addItem: $value")
        val mutableValue = value.toMutableList()
        val isNumEven = item.number?.let { item.isEven(it) }
            if (isNumEven != true){
                Log.d("TAG", "Number: $isNumEven")
                mutableValue.add(item)
            }else{
                mutableValue.add(0,item)
        }
        _dataFlow.emit(mutableValue)
    }
}