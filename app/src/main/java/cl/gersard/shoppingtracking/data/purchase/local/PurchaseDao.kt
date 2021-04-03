package cl.gersard.shoppingtracking.data.purchase.local

import androidx.room.*

@Dao
interface PurchaseDao {

//    @Transaction
//    @Query("SELECT * FROM Purchase")
//    suspend fun getPurchasesGroupedByProduct(): List<PurchaseDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurchase(purchase: PurchaseEntity): Long

}