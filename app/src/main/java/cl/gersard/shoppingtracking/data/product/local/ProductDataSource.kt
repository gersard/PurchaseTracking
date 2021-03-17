package cl.gersard.shoppingtracking.data.product.local

import cl.gersard.shoppingtracking.data.product.local.model.ProductWithPurchases

interface ProductDataSource {
    suspend fun getAllProducts(): List<ProductWithPurchases>
}