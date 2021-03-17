package cl.gersard.shoppingtracking.data.purchase

import cl.gersard.shoppingtracking.data.market.MarketMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object PurchaseModule {

    @Provides
    fun providesPurchaseMapper(marketMapper: MarketMapper) = PurchaseMapper(marketMapper)

}