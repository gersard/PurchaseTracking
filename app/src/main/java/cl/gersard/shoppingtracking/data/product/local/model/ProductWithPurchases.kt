package cl.gersard.shoppingtracking.data.product.local.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import cl.gersard.shoppingtracking.data.brand.BrandEntity
import cl.gersard.shoppingtracking.data.purchase.local.PurchaseDetailEntity
import cl.gersard.shoppingtracking.data.purchase.local.PurchaseEntity


data class ProductWithPurchases(

    @Embedded val product: ProductEntity,
    @Relation(
        entity = PurchaseEntity::class,
        parentColumn = "productId",
        entityColumn = "product_owner_id"
    )
    val purchases: List<PurchaseDetailEntity>,
    @Relation(
        parentColumn = "brandOwnerId",
        entityColumn = "brandId"
    )
    val brand: BrandEntity
)
