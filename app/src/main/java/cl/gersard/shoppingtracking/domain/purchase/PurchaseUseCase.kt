package cl.gersard.shoppingtracking.domain.purchase

interface PurchaseUseCase {

    suspend fun insertPurchase(purchase: Purchase)

}