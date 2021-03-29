package cl.gersard.shoppingtracking.data.purchase

import cl.gersard.shoppingtracking.domain.purchase.Purchase

interface PurchaseRepository {

    suspend fun insertPurchase(purchase: Purchase): Boolean

}