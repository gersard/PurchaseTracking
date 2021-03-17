package cl.gersard.shoppingtracking.data.purchase

import androidx.room.Embedded
import androidx.room.Relation
import cl.gersard.shoppingtracking.data.brand.BrandEntity
import cl.gersard.shoppingtracking.data.market.MarketEntity

data class PurchaseDetailEntity(
    @Embedded
    val purchase: PurchaseEntity,
    @Relation(parentColumn = "purchaseId", entityColumn = "marketId")
    val market: MarketEntity,
)
