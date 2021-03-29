package cl.gersard.shoppingtracking.data.purchase

import cl.gersard.shoppingtracking.data.purchase.local.PurchaseDataSource
import cl.gersard.shoppingtracking.domain.purchase.Purchase
import timber.log.Timber
import javax.inject.Inject

class PurchaseRepositoryImpl @Inject constructor(private val dataSource: PurchaseDataSource, private val mapper: PurchaseMapper) :
    PurchaseRepository {

    override suspend fun insertPurchase(purchase: Purchase): Boolean = try {
        dataSource.insertPurchase(mapper.mapToPurchaseEntity(purchase))
        true
    } catch (e: Exception) {
        Timber.e(e)
        false
    }

}