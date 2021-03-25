package cl.gersard.shoppingtracking.di

import cl.gersard.shoppingtracking.core.AppDatabase
import cl.gersard.shoppingtracking.data.brand.BrandMapper
import cl.gersard.shoppingtracking.data.market.MarketMapper
import cl.gersard.shoppingtracking.data.product.ProductMapper
import cl.gersard.shoppingtracking.data.purchase.PurchaseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object PurchaseModule {

//    @Provides
//    fun providesProductDao(db: AppDatabase) = db.productDao()

    @Provides
    fun providesPurchaseMapper(marketMapper: MarketMapper) = PurchaseMapper(marketMapper)

}