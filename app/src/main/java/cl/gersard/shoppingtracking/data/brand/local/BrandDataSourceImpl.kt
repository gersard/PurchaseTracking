package cl.gersard.shoppingtracking.data.brand.local

import cl.gersard.shoppingtracking.data.brand.BrandEntity
import javax.inject.Inject

class BrandDataSourceImpl @Inject constructor(private val brandDao: BrandDao) : BrandDataSource {

    override suspend fun insertBrand(brandEntity: BrandEntity): Long {
        return brandDao.insertBrand(brandEntity)
    }

}