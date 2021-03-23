package cl.gersard.shoppingtracking.data.purchase.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import cl.gersard.shoppingtracking.data.purchase.PurchaseDetailEntity

@Dao
interface PurchaseDao {

    @Transaction
    @Query("SELECT * FROM Purchase")
    suspend fun getPurchasesGroupedByProduct(): List<PurchaseDetailEntity>

}