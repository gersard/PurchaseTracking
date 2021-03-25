package cl.gersard.shoppingtracking.data.purchase

import androidx.room.Embedded
import androidx.room.Relation
import cl.gersard.shoppingtracking.data.market.MarketEntity

class PurchaseDetailEntity(
    @Embedded
    val purchase: PurchaseEntity,
    @Relation(
        parentColumn = "market_owner_id",
        entityColumn = "marketId"
    )
    val market: MarketEntity,
)