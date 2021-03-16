package cl.gersard.shoppingtracking.data.purchase

import androidx.room.Embedded
import androidx.room.Relation
import cl.gersard.shoppingtracking.data.BrandEntity
import cl.gersard.shoppingtracking.data.MarketEntity

data class PurchaseDetailEntity(
    @Embedded
    val purchase: PurchaseEntity,
    @Relation(parentColumn = "purchaseId", entityColumn = "brandId")
    val brand: BrandEntity,
    @Relation(parentColumn = "purchaseId", entityColumn = "marketId")
    val market: MarketEntity,
)
