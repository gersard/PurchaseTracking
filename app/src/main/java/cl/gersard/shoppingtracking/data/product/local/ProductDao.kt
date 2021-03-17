package cl.gersard.shoppingtracking.data.product.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import cl.gersard.shoppingtracking.data.product.local.model.ProductWithPurchases

@Dao
interface ProductDao {

    @Transaction
    @Query("SELECT * FROM product")
    suspend fun getProductsWithPurchases(): List<ProductWithPurchases>

}