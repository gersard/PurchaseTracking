package cl.gersard.shoppingtracking.data.purchase

import androidx.room.Embedded
import androidx.room.Relation
import cl.gersard.shoppingtracking.data.brand.BrandEntity
import cl.gersard.shoppingtracking.data.market.MarketEntity
import cl.gersard.shoppingtracking.data.product.local.model.ProductEntity
import cl.gersard.shoppingtracking.domain.Product

data class PurchaseDetailEntity(
    @Embedded
    val purchase: PurchaseEntity,
    @Relation(parentColumn = "purchaseId", entityColumn = "marketId")
    val market: MarketEntity,
    @Relation(parentColumn = "purchaseId", entityColumn = "productId")
    val products: List<ProductEntity>,
)
