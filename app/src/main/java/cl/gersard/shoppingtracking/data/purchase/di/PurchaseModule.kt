package cl.gersard.shoppingtracking.data.purchase.di

import cl.gersard.shoppingtracking.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object PurchaseModule {

    @Provides
    fun providesPurchaseDao(db: AppDatabase) = db.productDao()

//    @Provides
//    fun providesProductMapper(brandMapper: BrandMapper, purchaseMapper: PurchaseMapper) = ProductMapper(brandMapper)

}