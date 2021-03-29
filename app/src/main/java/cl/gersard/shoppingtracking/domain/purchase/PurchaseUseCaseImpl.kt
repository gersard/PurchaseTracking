package cl.gersard.shoppingtracking.domain.purchase

import cl.gersard.shoppingtracking.data.purchase.PurchaseRepository
import java.util.*
import javax.inject.Inject

class PurchaseUseCaseImpl @Inject constructor(private val repository: PurchaseRepository) : PurchaseUseCase {

    override suspend fun insertPurchase(purchase: Purchase) {
        purchase.note.capitalize(Locale.ROOT)
        repository.insertPurchase(purchase)
    }

}