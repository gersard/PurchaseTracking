package cl.gersard.shoppingtracking.data.product

import cl.gersard.shoppingtracking.data.product.local.ProductDataSource
import cl.gersard.shoppingtracking.domain.product.Product
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val productMapper: ProductMapper
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return productMapper.mapToProductDomain(dataSource.getAllProducts())
    }
}