package cl.gersard.shoppingtracking.di.purchase

import cl.gersard.shoppingtracking.data.purchase.PurchaseRepository
import cl.gersard.shoppingtracking.data.purchase.PurchaseRepositoryImpl
import cl.gersard.shoppingtracking.data.purchase.local.PurchaseDataSource
import cl.gersard.shoppingtracking.data.purchase.local.PurchaseDataSourceImpl
import cl.gersard.shoppingtracking.domain.purchase.PurchaseUseCase
import cl.gersard.shoppingtracking.domain.purchase.PurchaseUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class PurchaseArchModule {

    @Binds
    abstract fun bindsPurchaseDataSource(dataSourceImpl: PurchaseDataSourceImpl): PurchaseDataSource

    @Binds
    abstract fun bindsPurchaseRepository(repoImpl: PurchaseRepositoryImpl): PurchaseRepository

    @Binds
    abstract fun bindsPurchaseUseCase(useCase: PurchaseUseCaseImpl): PurchaseUseCase
}