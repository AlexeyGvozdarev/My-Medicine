package com.example.mymedicines

data class Item(
    val value: String,
    val number: Int? = null
){
    override fun toString(): String {
        return "$value $number"
    }
    fun isEven(number: Int): Boolean = number % 2 != 0

}
