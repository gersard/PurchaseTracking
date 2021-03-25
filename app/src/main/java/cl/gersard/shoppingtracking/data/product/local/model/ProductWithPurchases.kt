package cl.gersard.shoppingtracking.data.product.local.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import cl.gersard.shoppingtracking.data.brand.BrandEntity
import cl.gersard.shoppingtracking.data.purchase.PurchaseDetailEntity
import cl.gersard.shoppingtracking.data.purchase.PurchaseEntity
import cl.gersard.shoppingtracking.domain.Product


data class ProductWithPurchases(

    @Embedded val product: ProductEntity,
    @Relation(
        entity = PurchaseEntity::class,
        parentColumn = "productId",
        entityColumn = "purchaseId",
        associateBy = Junction(ProductPurchaseCrossRef::class)
    )
    val purchases: List<PurchaseDetailEntity>,
    @Relation(
        parentColumn = "brandOwnerId",
        entityColumn = "brandId"
    )
    val brand: BrandEntity
)
