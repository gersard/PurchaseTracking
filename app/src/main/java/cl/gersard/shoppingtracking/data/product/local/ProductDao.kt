package cl.gersard.shoppingtracking.data.product.local

import androidx.room.*
import cl.gersard.shoppingtracking.data.product.local.model.ProductEntity
import cl.gersard.shoppingtracking.data.product.local.model.ProductPurchaseCrossRef
import cl.gersard.shoppingtracking.data.product.local.model.ProductWithBrand
import cl.gersard.shoppingtracking.data.product.local.model.ProductWithPurchases

@Dao
interface ProductDao {

    @Transaction
    @Query("SELECT * FROM product")
    suspend fun getProductsWithPurchases(): List<ProductWithPurchases>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProductPurchase(productPurchaseCrossRef: ProductPurchaseCrossRef): Long

    @Query("SELECT * FROM product WHERE barcode = :barcode")
    suspend fun getProduct(barcode: String): ProductWithBrand?

}