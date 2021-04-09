package cl.gersard.shoppingtracking.data.product.local

import androidx.room.*
import cl.gersard.shoppingtracking.data.product.local.model.ProductEntity
import cl.gersard.shoppingtracking.data.product.local.model.ProductWithBrand
import cl.gersard.shoppingtracking.data.product.local.model.ProductWithPurchases

@Dao
interface ProductDao {

    @Transaction
    @Query("SELECT * FROM product")
    suspend fun getProductsWithPurchases(): List<ProductWithPurchases>

    @Insert()
    suspend fun insertProduct(product: ProductEntity): Long

    @Transaction
    @Query("SELECT * FROM product WHERE barcode = :barcode")
    suspend fun getProduct(barcode: String): ProductWithBrand?

    @Update
    suspend fun updateProduct(product: ProductEntity): Int

}