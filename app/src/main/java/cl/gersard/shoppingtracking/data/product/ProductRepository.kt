package cl.gersard.shoppingtracking.data.product

import cl.gersard.shoppingtracking.domain.product.Product
import cl.gersard.shoppingtracking.domain.product.ProductInsert

interface ProductRepository {

    suspend fun getProducts(): List<Product>

    suspend fun insertProduct(product: ProductInsert): Long

    suspend fun insertProductPurchase(idPurchase: Long, idProduct: Long): Boolean

    suspend fun getProduct(barcode: String): Product?

}