package cl.gersard.shoppingtracking.data.product.local

import cl.gersard.shoppingtracking.data.product.local.model.ProductWithPurchases
import javax.inject.Inject

class ProductDataSourceImpl @Inject constructor(private val productDao: ProductDao) : ProductDataSource {

    override suspend fun getAllProducts(): List<ProductWithPurchases> = productDao.getProductsWithPurchases()
}