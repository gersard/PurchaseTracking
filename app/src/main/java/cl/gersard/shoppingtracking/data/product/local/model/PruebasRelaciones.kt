package cl.gersard.shoppingtracking.data.product.local.model

import androidx.room.Embedded
import androidx.room.Relation
import cl.gersard.shoppingtracking.data.brand.BrandEntity

class PruebasRelaciones(
    @Embedded
    val brand: BrandEntity,
    @Relation(
        parentColumn = "brandId",
        entityColumn = "brandOwnerId"
    )
    val productWithPurchases: ProductWithPurchases
)