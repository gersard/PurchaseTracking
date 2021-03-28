package cl.gersard.shoppingtracking.data.market.local

import cl.gersard.shoppingtracking.data.market.MarketEntity


interface MarketDataSource {

    suspend fun insertMarket(marketEntity: MarketEntity): Long

}