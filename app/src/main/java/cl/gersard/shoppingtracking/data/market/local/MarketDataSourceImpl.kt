package cl.gersard.shoppingtracking.data.market.local

import cl.gersard.shoppingtracking.data.brand.local.BrandDao
import cl.gersard.shoppingtracking.data.market.MarketEntity
import javax.inject.Inject

class MarketDataSourceImpl @Inject constructor(private val marketDao: MarketDao) : MarketDataSource {

    override suspend fun insertBrand(marketEntity: MarketEntity): Long {
        return marketDao.insertMarket(marketEntity)
    }
}