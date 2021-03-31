package cl.gersard.shoppingtracking.domain.product

sealed class ProductState<out T> {
    data class Success<out T>(val data: T) : ProductState<T>()
    data class Error(var error: String) : ProductState<Nothing>()
    object Empty : ProductState<Nothing>()

}
