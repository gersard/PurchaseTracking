package cl.gersard.shoppingtracking.domain.purchase

import java.time.LocalDate

data class PurchaseInsert(
    val id: Long,
    val price: Int,
    val quantity: Int,
    val date: LocalDate,
    val marketId: Long,
    val discount: Boolean,
    val note: String,
    val productOwnerId: Long
)
