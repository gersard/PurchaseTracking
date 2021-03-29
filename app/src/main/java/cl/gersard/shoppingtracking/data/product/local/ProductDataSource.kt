package cl.gersard.shoppingtracking.data.product.local

import cl.gersard.shoppingtracking.data.product.local.model.ProductEntity
import cl.gersard.shoppingtracking.data.product.local.model.ProductPurchaseCrossRef
import cl.gersard.shoppingtracking.data.product.local.model.ProductWithPurchases

interface ProductDataSource {

    suspend fun getAllProducts(): List<ProductWithPurchases>

    suspend fun insertProduct(productEntity: ProductEntity): Long

    suspend fun insertProductPurchase(productPurchaseCrossRef: ProductPurchaseCrossRef): Long

}