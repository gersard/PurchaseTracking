package cl.gersard.shoppingtracking.data.market

import cl.gersard.shoppingtracking.domain.market.Market

interface MarketRepository {

    suspend fun insertBrand(market: Market): Long

    suspend fun getAllMarkets(): List<Market>
    suspend fun getMarket(marketName: String): Market?

}