package cl.gersard.shoppingtracking.data.product

import cl.gersard.shoppingtracking.data.product.local.ProductDataSource
import cl.gersard.shoppingtracking.domain.product.Product
import cl.gersard.shoppingtracking.domain.product.ProductInsertUpdate
import timber.log.Timber
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val productMapper: ProductMapper
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return productMapper.mapToProductDomain(dataSource.getAllProducts())
    }

    override suspend fun insertProduct(product: ProductInsertUpdate): Long = try {
        dataSource.insertProduct(productMapper.mapToProductEntity(product))
    } catch (e: Exception) {
        Timber.e(e)
        -1
    }

    override suspend fun getProduct(barcode: String): Product? {
        val product = dataSource.getProduct(barcode)
        return if (product != null) {
            productMapper.mapToProductDomain(product)
        } else {
            null
        }
    }

    override suspend fun updateProduct(product: ProductInsertUpdate): Int {
        Timber.d("$product")
        return dataSource.updateProduct(productMapper.mapToProductEntity(product))
    }
}