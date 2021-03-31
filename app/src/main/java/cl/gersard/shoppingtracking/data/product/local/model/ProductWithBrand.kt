package cl.gersard.shoppingtracking.data.product.local.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import cl.gersard.shoppingtracking.data.brand.BrandEntity
import cl.gersard.shoppingtracking.data.purchase.PurchaseDetailEntity
import cl.gersard.shoppingtracking.data.purchase.PurchaseEntity


data class ProductWithBrand(
    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "brandOwnerId",
        entityColumn = "brandId"
    )
    val brand: BrandEntity
)
