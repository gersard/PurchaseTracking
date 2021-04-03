package cl.gersard.shoppingtracking.domain.market

interface MarketUseCase {

    suspend fun insertMarket(name: String): Long

    suspend fun getMarkets(): List<Market>

    suspend fun getMarket(marketName: String): Market?

}