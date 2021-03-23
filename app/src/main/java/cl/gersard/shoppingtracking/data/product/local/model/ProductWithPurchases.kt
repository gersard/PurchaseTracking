package cl.gersard.shoppingtracking.data.product.local.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import cl.gersard.shoppingtracking.data.brand.BrandEntity
import cl.gersard.shoppingtracking.data.purchase.PurchaseEntity


data class ProductWithPurchases(
    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "productId",
        entityColumn = "purchaseId",
        associateBy = Junction(ProductPurchaseCrossRef::class)
    )
    val purchases: List<PurchaseEntity>,
    @Relation(
        parentColumn = "productId",
        entityColumn = "productOwnerId"
    )
    val brand: BrandEntity
)
