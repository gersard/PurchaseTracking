package cl.gersard.shoppingtracking.data.market

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object MarketModule {

    @Provides
    fun providesMarketMapper() = MarketMapper()

}