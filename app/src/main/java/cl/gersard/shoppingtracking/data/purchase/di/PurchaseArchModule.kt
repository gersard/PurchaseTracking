package cl.gersard.shoppingtracking.data.purchase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class PurchaseArchModule {

//    @Binds
//    abstract fun bindsDataSource(dataSourceImpl: ProductDataSourceImpl): ProductDataSource
//
//    @Binds
//    abstract fun bindsRepository(repoImpl: ProductRepositoryImpl): ProductRepository
}