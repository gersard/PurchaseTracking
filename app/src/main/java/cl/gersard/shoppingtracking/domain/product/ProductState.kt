package cl.gersard.shoppingtracking.domain.product

sealed class ProductState {
    data class Success(var products: List<Product>) : ProductState()
    data class Error(var error: String): ProductState()
    object Empty: ProductState()

}
