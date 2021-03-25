package cl.gersard.shoppingtracking.di

import cl.gersard.shoppingtracking.data.product.ProductRepository
import cl.gersard.shoppingtracking.data.product.ProductRepositoryImpl
import cl.gersard.shoppingtracking.data.product.local.ProductDataSource
import cl.gersard.shoppingtracking.data.product.local.ProductDataSourceImpl
import cl.gersard.shoppingtracking.domain.ProductUseCase
import cl.gersard.shoppingtracking.domain.ProductUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ProductArchModule {

    @Binds
    abstract fun bindsProductDataSource(dataSourceImpl: ProductDataSourceImpl): ProductDataSource

    @Binds
    abstract fun bindsProductRepository(repoImpl: ProductRepositoryImpl): ProductRepository

    @Binds
    abstract fun bindsProductUseCase(useCase: ProductUseCaseImpl): ProductUseCase
}