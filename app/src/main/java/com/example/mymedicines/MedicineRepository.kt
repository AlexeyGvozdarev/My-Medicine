package com.example.mymedicines

class MedicineRepository: ItemRepository {
    private val items = mutableListOf<Item>()

    init {
        items.addAll(
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
    override suspend fun getItems(): List<Item> {
        return items.toList()
    }

    override suspend fun addItem(item: Item) {
        items.add(item)
    }

}