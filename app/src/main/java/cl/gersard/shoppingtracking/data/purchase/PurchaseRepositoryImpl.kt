package cl.gersard.shoppingtracking.data.purchase

import cl.gersard.shoppingtracking.data.purchase.local.PurchaseDataSource
import cl.gersard.shoppingtracking.domain.purchase.PurchaseInsert
import timber.log.Timber
import javax.inject.Inject

class PurchaseRepositoryImpl @Inject constructor(private val dataSource: PurchaseDataSource, private val mapper: PurchaseMapper) :
    PurchaseRepository {

    override suspend fun insertPurchase(purchase: PurchaseInsert): Long {
        Timber.d("$purchase")
        return dataSource.insertPurchase(mapper.mapToPurchaseEntity(purchase))
    }

}