package cl.gersard.shoppingtracking.data.product.local

import cl.gersard.shoppingtracking.data.product.local.model.ProductEntity
import cl.gersard.shoppingtracking.data.product.local.model.ProductWithBrand
import cl.gersard.shoppingtracking.data.product.local.model.ProductWithPurchases
import javax.inject.Inject

class ProductDataSourceImpl @Inject constructor(private val productDao: ProductDao) : ProductDataSource {

    override suspend fun getAllProducts(): List<ProductWithPurchases> = productDao.getProductsWithPurchases()

    override suspend fun insertProduct(productEntity: ProductEntity): Long = productDao.insertProduct(productEntity)

    override suspend fun getProduct(barcode: String): ProductWithBrand? = productDao.getProduct(barcode)

    override suspend fun updateProduct(product: ProductEntity): Int = productDao.updateProduct(product)
}