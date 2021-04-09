package cl.gersard.shoppingtracking.domain.purchase

import cl.gersard.shoppingtracking.domain.market.Market
import cl.gersard.shoppingtracking.domain.product.Product
import java.time.LocalDate
import java.time.LocalDateTime

data class Purchase(
    val id: Long,
    val price: Int,
    val quantity: Int,
    val date: LocalDate,
    val market: Market,
    val discount: Boolean,
    val note: String,
    val productId: Long
)
