package cl.gersard.shoppingtracking.data.purchase.local

import cl.gersard.shoppingtracking.data.purchase.PurchaseEntity


interface PurchaseDataSource {

    suspend fun insertPurchase(purchase: PurchaseEntity): Long


}