package cl.gersard.shoppingtracking.data.brand

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object BrandModule {

    @Provides
    fun providesBrandMapper() = BrandMapper()

}