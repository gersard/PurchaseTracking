package cl.gersard.shoppingtracking.domain.product

import cl.gersard.shoppingtracking.data.product.ProductRepository
import java.util.*
import javax.inject.Inject

class ProductUseCaseImpl @Inject constructor(private val repository: ProductRepository) : ProductUseCase {

    override suspend fun getProductsWithPurchases(): List<Product> {
        return repository.getProducts()
    }

    override suspend fun insertProduct(product: Product) {
        repository.insertProduct(product)
    }

    override suspend fun insertProductPurchase(idProduct: Long, idPurchase: Long) {
        repository.insertProductPurchase(idProduct, idPurchase)
    }
}