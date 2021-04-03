package cl.gersard.shoppingtracking.data.purchase.local


interface PurchaseDataSource {

    suspend fun insertPurchase(purchase: PurchaseEntity): Long


}