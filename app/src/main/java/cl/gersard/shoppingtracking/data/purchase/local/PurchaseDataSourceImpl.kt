package cl.gersard.shoppingtracking.data.purchase.local

import javax.inject.Inject

class PurchaseDataSourceImpl @Inject constructor(private val purchaseDao: PurchaseDao) : PurchaseDataSource {

    override suspend fun insertPurchase(purchase: PurchaseEntity): Long = purchaseDao.insertPurchase(purchase)

}