package cl.gersard.shoppingtracking.data.product.local.model

import androidx.room.Embedded
import androidx.room.Relation
import cl.gersard.shoppingtracking.data.brand.BrandEntity
import cl.gersard.shoppingtracking.data.purchase.PurchaseDetailEntity
import cl.gersard.shoppingtracking.data.purchase.PurchaseEntity

data class ProductWithPurchases(
    @Embedded
    val product: ProductEntity,
    @Relation(parentColumn = "productId", entityColumn = "brandId")
    val brand: BrandEntity,
    @Relation(parentColumn = "productId", entityColumn = "purchaseId")
    val purchases: List<PurchaseDetailEntity>,
)
