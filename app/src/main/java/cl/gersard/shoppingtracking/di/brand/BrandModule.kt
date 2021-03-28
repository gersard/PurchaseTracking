package cl.gersard.shoppingtracking.di.brand

import cl.gersard.shoppingtracking.core.AppDatabase
import cl.gersard.shoppingtracking.data.brand.BrandMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object BrandModule {

    @Provides
    fun providesBrandDao(db: AppDatabase) = db.brandDao()

    @Provides
    fun providesBrandMapper() = BrandMapper()

}