package cl.gersard.shoppingtracking.data.product.local.model

import androidx.room.Embedded
import androidx.room.Relation
import cl.gersard.shoppingtracking.data.brand.BrandEntity


data class ProductWithBrand(
    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "brandOwnerId",
        entityColumn = "brandId"
    )
    val brand: BrandEntity
)
