package cl.gersard.shoppingtracking.data.product.di

import cl.gersard.shoppingtracking.data.product.ProductRepository
import cl.gersard.shoppingtracking.data.product.ProductRepositoryImpl
import cl.gersard.shoppingtracking.data.product.local.ProductDataSource
import cl.gersard.shoppingtracking.data.product.local.ProductDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ProductArchModule {

    @Binds
    abstract fun bindsDataSource(dataSourceImpl: ProductDataSourceImpl): ProductDataSource

    @Binds
    abstract fun bindsRepository(repoImpl: ProductRepositoryImpl): ProductRepository
}