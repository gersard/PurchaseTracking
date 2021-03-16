package cl.gersard.shoppingtracking.data.product

import androidx.room.Embedded
import androidx.room.Relation
import cl.gersard.shoppingtracking.data.purchase.PurchaseEntity

data class ProductWithPurchases(
    @Embedded
    val product: ProductEntity,
    @Relation(parentColumn = "productId", entityColumn = "purchaseId")
    val purchases: List<PurchaseEntity>,
)
