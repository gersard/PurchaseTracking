package cl.gersard.shoppingtracking.di.product

import cl.gersard.shoppingtracking.core.AppDatabase
import cl.gersard.shoppingtracking.data.brand.BrandMapper
import cl.gersard.shoppingtracking.data.product.ProductMapper
import cl.gersard.shoppingtracking.data.purchase.PurchaseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object ProductModule {

    @Provides
    fun providesProductDao(db: AppDatabase) = db.productDao()

    @Provides
    fun providesProductMapper(purchaseMapper: PurchaseMapper, brandMapper: BrandMapper) = ProductMapper(purchaseMapper, brandMapper)

}