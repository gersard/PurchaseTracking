package cl.gersard.shoppingtracking.data.market.local

import cl.gersard.shoppingtracking.data.market.MarketEntity
import javax.inject.Inject

class MarketDataSourceImpl @Inject constructor(private val marketDao: MarketDao) : MarketDataSource {

    override suspend fun insertMarket(marketEntity: MarketEntity): Long = marketDao.insertMarket(marketEntity)

    override suspend fun getAllMarkets(): List<MarketEntity> = marketDao.getAllMarkets()
}