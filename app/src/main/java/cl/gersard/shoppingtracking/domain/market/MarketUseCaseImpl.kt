package cl.gersard.shoppingtracking.domain.market

import cl.gersard.shoppingtracking.data.market.MarketRepository
import java.util.*
import javax.inject.Inject

class MarketUseCaseImpl @Inject constructor(private val repository: MarketRepository) : MarketUseCase {

    override suspend fun insertMarket(name: String): Long {
        if (name.isNotEmpty()) {
            return repository.insertBrand(Market(0, name.capitalize(Locale.ROOT)))
        } else {
            throw Exception("Name must not be empty")
        }
    }

    override suspend fun getMarkets(): List<Market> {
        return repository.getAllMarkets()
    }
}