package com.example.mymedicines

import com.example.mymedicines.domain.ItemRepository
import com.example.mymedicines.domain.MedicineRepository

object AppComponents {
    val medicineRepository: ItemRepository by lazy { MedicineRepository() }
}
