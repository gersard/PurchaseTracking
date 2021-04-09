package cl.gersard.shoppingtracking.domain.product

import cl.gersard.shoppingtracking.data.product.ProductRepository
import javax.inject.Inject

class ProductUseCaseImpl @Inject constructor(private val repository: ProductRepository) : ProductUseCase {

    override suspend fun getProductsWithPurchases(): List<Product> {
        return repository.getProducts()
    }

    override suspend fun insertProduct(id: Long, name: String, description: String, barcode: String, brandId: Long, note: String) : Long{
        val product = ProductInsertUpdate(id, barcode, name, description, brandId, note)
        return repository.insertProduct(product)
    }

    override suspend fun searchProduct(barcode: String): ProductState<Product> {
        val product = repository.getProduct(barcode)
        return try {
            if (product != null) {
                ProductState.Success(product)
            } else {
                ProductState.Empty
            }
        } catch (e: Exception) {
            ProductState.Error("An error has occurred while searching")
        }
    }

    override suspend fun updateProduct(product: ProductInsertUpdate): Int {
        return repository.updateProduct(product)
    }
}