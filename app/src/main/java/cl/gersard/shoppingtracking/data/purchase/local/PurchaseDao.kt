package cl.gersard.shoppingtracking.data.purchase.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface PurchaseDao {

//    @Transaction
//    @Query("SELECT * FROM Purchase")
//    suspend fun getPurchasesGroupedByProduct(): List<PurchaseDetailEntity>

}