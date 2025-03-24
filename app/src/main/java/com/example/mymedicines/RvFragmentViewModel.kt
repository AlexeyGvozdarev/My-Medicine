package com.example.mymedicines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class RvFragmentViewModel:ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items:LiveData<List<Item>>get() = _items

    init {
        loadItems()
    }
    private fun loadItems(){
        val itemList = listOf(
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
        _items.value = itemList
    }
}