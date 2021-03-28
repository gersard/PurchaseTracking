package cl.gersard.shoppingtracking.domain.product

interface ProductUseCase {

    suspend fun getProductsWithPurchases(): List<Product>

}