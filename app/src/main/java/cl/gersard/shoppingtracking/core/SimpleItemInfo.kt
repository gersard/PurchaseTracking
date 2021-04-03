package cl.gersard.shoppingtracking.core

interface SimpleItemInfo {
    fun id(): Long
    fun name(): String
    fun type(): Int

    object TYPES {
        val BRAND = 1
        val MARKET = 2
    }
}