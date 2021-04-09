package cl.gersard.shoppingtracking.domain.purchase

import java.time.LocalDate

interface PurchaseUseCase {

    suspend fun insertPurchase(productId: Long, price: Int, quantity: Int, date: LocalDate, marketId: Long, discount: Boolean, note: String): Long

}