package cl.gersard.shoppingtracking.data.market.local

import cl.gersard.shoppingtracking.data.market.MarketEntity


interface MarketDataSource {

    suspend fun insertMarket(marketEntity: MarketEntity): Long

    suspend fun getAllMarkets(): List<MarketEntity>

    suspend fun getMarket(marketName: String): MarketEntity?
}