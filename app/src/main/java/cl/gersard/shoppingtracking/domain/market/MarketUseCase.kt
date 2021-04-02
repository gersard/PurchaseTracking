package cl.gersard.shoppingtracking.domain.market

interface MarketUseCase {

    suspend fun insertMarket(name: String)

    suspend fun getMarkets(): List<Market>

}