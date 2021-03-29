package cl.gersard.shoppingtracking.domain.purchase

import cl.gersard.shoppingtracking.domain.market.Market
import java.time.LocalDateTime

data class Purchase(
    val id: Long,
    val price: Int,
    val quantity: Int,
    val date: LocalDateTime,
    val market: Market,
    val discount: Boolean,
    val note: String
)
