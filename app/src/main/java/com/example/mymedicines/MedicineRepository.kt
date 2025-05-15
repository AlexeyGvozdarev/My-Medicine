package com.example.mymedicines

import android.util.Log
import kotlinx.coroutines.flow.Flow
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
    override suspend fun addItem(item: Item) {

        val value = _dataFlow.value
        Log.d("TAG", "addItem: $value")
//        val regex = "\\d+".toRegex()
//        val num = regex.find(item.toString())?.value?.toIntOrNull() ?: return

        val mutableValue = value.toMutableList()
        val num = item.number
        if (num != null) {
            if (num % 2 == 0) {
                Log.d("TAG", "Number: $num")
                mutableValue.add(item)
            } else {

                mutableValue.add(0,item)
            }
        }
        _dataFlow.emit(mutableValue)
    }
}