package cl.gersard.shoppingtracking.data.market

import cl.gersard.shoppingtracking.data.market.local.MarketDataSource
import cl.gersard.shoppingtracking.domain.market.Market
import timber.log.Timber
import javax.inject.Inject

class MarketRepositoryImpl @Inject constructor(private val dataSource: MarketDataSource, private val marketMapper: MarketMapper) : MarketRepository {

    override suspend fun insertBrand(market: Market): Long {
        return try {
            dataSource.insertMarket(marketMapper.mapToMarketEntity(market))
        } catch (e: Exception) {
            Timber.e(e)
            -1
        }
    }

    override suspend fun getAllMarkets(): List<Market> = try {
        marketMapper.mapToMarketDomain(dataSource.getAllMarkets())
    } catch (e: Exception) {
        emptyList()
    }
}