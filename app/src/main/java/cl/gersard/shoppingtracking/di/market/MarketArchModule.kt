package cl.gersard.shoppingtracking.di.market

import cl.gersard.shoppingtracking.data.market.MarketRepository
import cl.gersard.shoppingtracking.data.market.MarketRepositoryImpl
import cl.gersard.shoppingtracking.data.market.local.MarketDataSource
import cl.gersard.shoppingtracking.data.market.local.MarketDataSourceImpl
import cl.gersard.shoppingtracking.domain.market.MarketUseCase
import cl.gersard.shoppingtracking.domain.market.MarketUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class MarketArchModule {

    @Binds
    abstract fun bindsMarketDataSource(dataSourceImpl: MarketDataSourceImpl): MarketDataSource

    @Binds
    abstract fun bindsMarketRepository(repoImpl: MarketRepositoryImpl): MarketRepository

    @Binds
    abstract fun bindsMarketUseCase(useCase: MarketUseCaseImpl): MarketUseCase
}