package cl.gersard.shoppingtracking.data.product

import cl.gersard.shoppingtracking.domain.product.Product
import cl.gersard.shoppingtracking.domain.product.ProductInsertUpdate

interface ProductRepository {

    suspend fun getProducts(): List<Product>

    suspend fun insertProduct(product: ProductInsertUpdate): Long

    suspend fun insertProductPurchase(idProduct: Long, idPurchase: Long): Boolean

    suspend fun getProduct(barcode: String): Product?

    suspend fun updateProduct(product: ProductInsertUpdate): Int

}