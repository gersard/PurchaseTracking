package cl.gersard.shoppingtracking.data.market

import cl.gersard.shoppingtracking.domain.market.Market

interface MarketRepository {

    suspend fun insertBrand(market: Market): Boolean

}