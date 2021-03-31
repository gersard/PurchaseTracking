package cl.gersard.shoppingtracking.data.brand

import cl.gersard.shoppingtracking.data.brand.local.BrandDataSource
import cl.gersard.shoppingtracking.domain.brand.Brand
import timber.log.Timber
import javax.inject.Inject

class BrandRepositoryImpl @Inject constructor(private val dataSource: BrandDataSource, private val brandMapper: BrandMapper) : BrandRepository {

    override suspend fun insertBrand(brand: Brand): Boolean {
        return try {
            dataSource.insertBrand(brandMapper.mapToBrandEntity(brand))
            true
        } catch (e: Exception) {
            Timber.e(e)
            false
        }
    }

    override suspend fun getAllBrands(): List<Brand> {
        return try {
            brandMapper.mapToBrandDomain(dataSource.getAllBrands())
        } catch (e: Exception) {
            emptyList()
        }
    }
}