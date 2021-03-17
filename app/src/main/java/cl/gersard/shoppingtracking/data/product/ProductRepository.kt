package cl.gersard.shoppingtracking.data.product

import cl.gersard.shoppingtracking.domain.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}