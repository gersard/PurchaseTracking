package cl.gersard.shoppingtracking.data.purchase

import cl.gersard.shoppingtracking.data.market.MarketMapper
import cl.gersard.shoppingtracking.domain.purchase.Purchase
import javax.inject.Inject

class PurchaseMapper @Inject constructor(private val marketMapper: MarketMapper,) {

    fun mapToPurchaseDomain(purchasesEntity: List<PurchaseDetailEntity>) = purchasesEntity.map {
        Purchase(
            it.purchase.purchaseId,
            it.purchase.total,
            it.purchase.total,
            it.purchase.date,
            marketMapper.mapToMarketDomain(it.market),
            it.purchase.discount,
            it.purchase.note
        )
    }

}