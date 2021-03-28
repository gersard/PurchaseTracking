package cl.gersard.shoppingtracking.data.market

import cl.gersard.shoppingtracking.domain.market.Market


class MarketMapper {

    fun mapToMarketDomain(marketEntity: MarketEntity) = Market(
        marketEntity.marketId, marketEntity.name
    )

}