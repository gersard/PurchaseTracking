package cl.gersard.shoppingtracking.data.market

import cl.gersard.shoppingtracking.data.market.local.MarketDataSource
import cl.gersard.shoppingtracking.domain.market.Market
import timber.log.Timber
import javax.inject.Inject

class MarketRepositoryImpl @Inject constructor(private val dataSource: MarketDataSource, private val marketMapper: MarketMapper) : MarketRepository {

    override suspend fun insertBrand(market: Market): Boolean {
        return try {
            dataSource.insertMarket(marketMapper.mapToMarketEntity(market))
            true
        } catch (e: Exception) {
            Timber.e(e)
            false
        }
    }
}