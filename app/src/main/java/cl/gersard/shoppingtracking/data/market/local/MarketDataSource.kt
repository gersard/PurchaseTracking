package cl.gersard.shoppingtracking.data.market.local

import cl.gersard.shoppingtracking.data.market.MarketEntity


interface MarketDataSource {

    suspend fun insertBrand(marketEntity: MarketEntity): Long

}