package cl.gersard.shoppingtracking.data.brand

import cl.gersard.shoppingtracking.data.brand.local.BrandDataSource
import cl.gersard.shoppingtracking.domain.brand.Brand
import timber.log.Timber
import javax.inject.Inject

class BrandRepositoryImpl @Inject constructor(private val dataSource: BrandDataSource, private val brandMapper: BrandMapper) : BrandRepository {

    override suspend fun insertBrand(brand: Brand): Long {
        return try {
            dataSource.insertBrand(brandMapper.mapToBrandEntity(brand))
        } catch (e: Exception) {
            Timber.e(e)
            -1
        }
    }

    override suspend fun getAllBrands(): List<Brand> {
        return try {
            brandMapper.mapToBrandDomain(dataSource.getAllBrands())
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getBrand(brandName: String): Brand? {
        val brandEntity = dataSource.getBrand(brandName)
        return if (brandEntity != null) {
            brandMapper.mapToBrandDomain(brandEntity)
        } else {
            null
        }
    }
}