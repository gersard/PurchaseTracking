package cl.gersard.shoppingtracking.data.market

import cl.gersard.shoppingtracking.domain.market.Market


class MarketMapper {

    fun mapToMarketDomain(marketEntity: List<MarketEntity>) = marketEntity.map { mapToMarketDomain(it) }
    fun mapToMarketDomain(marketEntity: MarketEntity) = Market(marketEntity.marketId, marketEntity.name)
    fun mapToMarketEntity(market: Market) = MarketEntity(market.id, market.name)

}