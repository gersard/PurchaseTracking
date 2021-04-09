package cl.gersard.shoppingtracking.data.product.local

import cl.gersard.shoppingtracking.data.product.local.model.ProductEntity
import cl.gersard.shoppingtracking.data.product.local.model.ProductWithBrand
import cl.gersard.shoppingtracking.data.product.local.model.ProductWithPurchases

interface ProductDataSource {

    suspend fun getAllProducts(): List<ProductWithPurchases>

    suspend fun insertProduct(productEntity: ProductEntity): Long

    suspend fun getProduct(barcode: String): ProductWithBrand?

    suspend fun updateProduct(product: ProductEntity): Int

}