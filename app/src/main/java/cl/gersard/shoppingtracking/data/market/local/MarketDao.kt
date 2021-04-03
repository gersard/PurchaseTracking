package cl.gersard.shoppingtracking.data.market.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import cl.gersard.shoppingtracking.data.market.MarketEntity

@Dao
interface MarketDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertMarket(marketEntity: MarketEntity): Long

    @Query("SELECT * FROM Market")
    suspend fun getAllMarkets(): List<MarketEntity>

    @Query("SELECT * From Market WHERE name = :marketName")
    suspend fun getMarket(marketName: String): MarketEntity?

}