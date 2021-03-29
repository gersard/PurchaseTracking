package cl.gersard.shoppingtracking.di.purchase

import cl.gersard.shoppingtracking.core.AppDatabase
import cl.gersard.shoppingtracking.data.market.MarketMapper
import cl.gersard.shoppingtracking.data.purchase.PurchaseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object PurchaseModule {

    @Provides
    fun providesPurchaseDao(db: AppDatabase) = db.purchaseDao()

    @Provides
    fun providesPurchaseMapper(marketMapper: MarketMapper) = PurchaseMapper(marketMapper)

}