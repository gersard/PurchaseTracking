package cl.gersard.shoppingtracking.di.brand

import cl.gersard.shoppingtracking.data.brand.BrandRepository
import cl.gersard.shoppingtracking.data.brand.BrandRepositoryImpl
import cl.gersard.shoppingtracking.data.brand.local.BrandDataSource
import cl.gersard.shoppingtracking.data.brand.local.BrandDataSourceImpl
import cl.gersard.shoppingtracking.domain.brand.BrandUseCase
import cl.gersard.shoppingtracking.domain.brand.BrandUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class BrandArchModule {

    @Binds
    abstract fun bindsBrandDataSource(dataSourceImpl: BrandDataSourceImpl): BrandDataSource

    @Binds
    abstract fun bindsBrandRepository(repoImpl: BrandRepositoryImpl): BrandRepository

    @Binds
    abstract fun bindsBrandUseCase(useCase: BrandUseCaseImpl): BrandUseCase
}