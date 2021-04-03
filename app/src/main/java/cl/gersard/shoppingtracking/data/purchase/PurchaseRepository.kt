package cl.gersard.shoppingtracking.data.purchase

import cl.gersard.shoppingtracking.domain.purchase.PurchaseInsert


interface PurchaseRepository {

    suspend fun insertPurchase(purchase: PurchaseInsert): Long

}