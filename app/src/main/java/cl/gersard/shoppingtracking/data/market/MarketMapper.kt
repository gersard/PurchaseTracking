package cl.gersard.shoppingtracking.data.market

import cl.gersard.shoppingtracking.domain.Market


class MarketMapper {

    fun mapToMarketDomain(marketEntity: MarketEntity) = Market(
        marketEntity.marketId, marketEntity.name
    )

}