package cl.gersard.shoppingtracking.domain

import java.time.LocalDateTime

data class Purchase(
    val id: Long,
    val product: Product,
    val total: Int,
    val quantity: Int,
    val date: LocalDateTime,
    val market: Market,
    val discount: Boolean,
    val note: String
)
