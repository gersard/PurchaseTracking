package cl.gersard.shoppingtracking.domain.purchase

import cl.gersard.shoppingtracking.data.purchase.PurchaseRepository
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

class PurchaseUseCaseImpl @Inject constructor(private val repository: PurchaseRepository) : PurchaseUseCase {

    override suspend fun insertPurchase(
        productId: Long,
        price: Int,
        quantity: Int,
        date: LocalDate,
        marketId: Long,
        discount: Boolean,
        note: String
    ): Long {
        val purchase = PurchaseInsert(0, price, quantity, date, marketId, discount, note, productId)
        return repository.insertPurchase(purchase)
    }

}