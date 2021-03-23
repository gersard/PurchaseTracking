package cl.gersard.shoppingtracking.domain

import cl.gersard.shoppingtracking.data.product.ProductRepository
import javax.inject.Inject

class ProductUseCaseImpl @Inject constructor(private val repository: ProductRepository) : ProductUseCase {

    override suspend fun getProductsWithPurchases(): List<Product> {
        return repository.getProducts()
    }
}