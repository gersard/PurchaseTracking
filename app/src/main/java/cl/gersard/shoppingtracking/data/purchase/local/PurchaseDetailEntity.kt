package cl.gersard.shoppingtracking.data.purchase.local

import androidx.room.Embedded
import androidx.room.Relation
import cl.gersard.shoppingtracking.data.market.MarketEntity
import cl.gersard.shoppingtracking.data.purchase.local.PurchaseEntity

class PurchaseDetailEntity(
    @Embedded
    val purchase: PurchaseEntity,
    @Relation(
        parentColumn = "market_owner_id",
        entityColumn = "marketId"
    )
    val market: MarketEntity,
)