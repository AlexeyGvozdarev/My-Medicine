package com.example.mymedicines

data class Item(
    val value: String,
    val number: Int? = null
){
    override fun toString(): String {
        return "$value $number"
    }
    fun isOdd(number: Int): Boolean {
        if (number % 2 == 0){
            return true
        }else return false
    }

}
