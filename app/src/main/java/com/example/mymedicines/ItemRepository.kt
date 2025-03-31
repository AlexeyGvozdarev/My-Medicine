package com.example.mymedicines

interface ItemRepository {
    suspend fun getItems(): List<Item>
    suspend fun addItem(item: Item)
}