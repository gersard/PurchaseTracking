package cl.gersard.shoppingtracking.data.product

import cl.gersard.shoppingtracking.data.product.local.ProductDataSource
import cl.gersard.shoppingtracking.data.product.local.model.ProductPurchaseCrossRef
import cl.gersard.shoppingtracking.domain.product.Product
import cl.gersard.shoppingtracking.domain.product.ProductInsert
import timber.log.Timber
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val productMapper: ProductMapper
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return productMapper.mapToProductDomain(dataSource.getAllProducts())
    }

    override suspend fun insertProduct(product: ProductInsert): Boolean = try {
        dataSource.insertProduct(productMapper.mapToProductEntity(product))
        true
    } catch (e: Exception) {
        Timber.e(e)
        false
    }

    override suspend fun insertProductPurchase(idPurchase: Long, idProduct: Long): Boolean = try {
        dataSource.insertProductPurchase(ProductPurchaseCrossRef(idProduct, idPurchase))
        true
    } catch (e: Exception) {
        Timber.e(e)
        false
    }

    override suspend fun getProduct(barcode: String): Product? {
        val product = dataSource.getProduct(barcode)
        return if (product != null) {
            productMapper.mapToProductDomain(product)
        } else {
            null
        }
    }
}