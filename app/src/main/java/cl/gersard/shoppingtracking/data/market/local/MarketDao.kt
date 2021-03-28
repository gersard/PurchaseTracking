package cl.gersard.shoppingtracking.data.market.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import cl.gersard.shoppingtracking.data.market.MarketEntity

@Dao
interface MarketDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertMarket(marketEntity: MarketEntity): Long

}