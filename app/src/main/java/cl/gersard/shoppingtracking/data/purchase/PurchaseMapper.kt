package cl.gersard.shoppingtracking.data.purchase

import cl.gersard.shoppingtracking.data.market.MarketMapper
import cl.gersard.shoppingtracking.data.purchase.local.PurchaseDetailEntity
import cl.gersard.shoppingtracking.data.purchase.local.PurchaseEntity
import cl.gersard.shoppingtracking.domain.purchase.Purchase
import cl.gersard.shoppingtracking.domain.purchase.PurchaseInsert
import javax.inject.Inject

class PurchaseMapper @Inject constructor(private val marketMapper: MarketMapper) {

    fun mapToPurchaseDomain(purchasesEntity: List<PurchaseDetailEntity>) = purchasesEntity.map {
        Purchase(
            it.purchase.purchaseId,
            it.purchase.price,
            it.purchase.quantity,
            it.purchase.date,
            marketMapper.mapToMarketDomain(it.market),
            it.purchase.discount,
            it.purchase.note,
            it.purchase.productOwnerId
        )
    }

    fun mapToPurchaseEntity(purchase: Purchase) = PurchaseEntity(
        purchase.id,
        purchase.price,
        purchase.quantity,
        purchase.date,
        purchase.discount,
        purchase.note,
        purchase.market.id,
        purchase.productId
    )

    fun mapToPurchaseEntity(purchase: PurchaseInsert) = PurchaseEntity(
        purchase.id,
        purchase.price,
        purchase.quantity,
        purchase.date,
        purchase.discount,
        purchase.note,
        purchase.marketId,
        purchase.productOwnerId
    )
}