package cl.gersard.shoppingtracking.domain

interface ProductUseCase {

    suspend fun getProductsWithPurchases(): List<Product>

}