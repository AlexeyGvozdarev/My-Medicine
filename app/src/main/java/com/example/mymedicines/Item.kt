package com.example.mymedicines

data class Item(
    val value: String,
    val number: Int? = null
) {
    override fun toString(): String {
        return if (number != null) "$value $number" else "$value"
    }

    fun isEven(): Boolean {
        if (number == null) return false
        return number % 2 == 0
    }


}
