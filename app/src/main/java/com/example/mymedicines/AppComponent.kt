package com.example.mymedicines

import com.example.mymedicines.domain.ItemRepository
import com.example.mymedicines.domain.MedicineRepository
import com.example.mymedicines.view.medecine.list.MedecineViewModelFactory
import com.example.mymedicines.view.medecine.list.RvFragmentViewModel

object AppComponents {
    val medicineRepository: ItemRepository by lazy { MedicineRepository() }
    
}
